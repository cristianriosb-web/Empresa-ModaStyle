    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.Scanner;

    public class Aplicacion {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            // Inicializar la empresa principal ModaStyle
            Empresa empresa = new Empresa("ModaStyle", "900123456-7", "Calle Principal #10-20, Armenia");
            int opcion;

            do {
                System.out.println("\n ========== MENU MODASTYLE =========== ");
                System.out.println("------ Sucursales ------");
                System.out.println("1. Registrar Sucursal");
                System.out.println("------ Prendas y Catálogo ------");
                System.out.println("2. Registrar Prenda");
                System.out.println("------ Personal (Clientes y Vendedores) ------");
                System.out.println("3. Registrar Cliente");
                System.out.println("4. Registrar Vendedor");
                System.out.println("------ Inventario ------");
                System.out.println("5. Registrar Existencias (Inventario en Sucursal)");
                System.out.println("6. Consultar Prendas Disponibles en una Sucursal");
                System.out.println("------ Ventas ------");
                System.out.println("7. Registrar y Confirmar Venta");
                System.out.println("------ Reportes y Consultas ------");
                System.out.println("8. Consultar Prendas Agotadas");
                System.out.println("9. Consultar Ventas de un Vendedor");
                System.out.println("10. Consultar Compras de un Cliente");
                System.out.println("11. Calcular Ingresos Totales de una Sucursal");
                System.out.println("12. Identificar Prenda Más Vendida en la Empresa");
                System.out.println("--------------------------------");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    // ============= 1. Registrar Sucursal =============
                    case 1:
                        System.out.println("----- Registrar Sucursal -----");
                        System.out.print("Ingrese el código de la sucursal: ");
                        String codSucursal = sc.nextLine();
                        System.out.print("Ingrese el nombre de la sucursal: ");
                        String nombreSucursal = sc.nextLine();
                        System.out.print("Ingrese la dirección: ");
                        String dirSucursal = sc.nextLine();
                        System.out.print("Ingrese la ciudad: ");
                        String ciudadSucursal = sc.nextLine();

                        Sucursal sucursal = new Sucursal(codSucursal, nombreSucursal, dirSucursal, ciudadSucursal);
                        if (empresa.registrarSucursal(sucursal)) {
                            System.out.println("Sucursal registrada exitosamente.");
                        } else {
                            System.out.println("La sucursal ya existe o no se pudo registrar.");
                        }
                        break;

                    // ============= 2. Registrar Prenda =============
                    case 2:
                        System.out.println("----- Registrar Prenda en el Catálogo -----");
                        System.out.print("Ingrese el código único (referencia) de la prenda: ");
                        String codPrenda = sc.nextLine();
                        System.out.print("Ingrese el nombre de la prenda: ");
                        String nombrePrenda = sc.nextLine();
                        System.out.print("Ingrese la marca: ");
                        String marcaPrenda = sc.nextLine();
                        System.out.print("Ingrese el precio base unitario: ");
                        double precioPrenda = sc.nextDouble();
                        sc.nextLine();

                        CategoriaPrenda categoriaPrenda = pedirCategoriaPrenda(sc);

                        Prenda prenda = new Prenda(codPrenda, nombrePrenda, marcaPrenda, precioPrenda, categoriaPrenda);
                        if (empresa.registrarPrenda(prenda)) {
                            System.out.println("Prenda registrada exitosamente.");
                        } else {
                            System.out.println("Ya existe una prenda con ese código (código duplicado no permitido).");
                        }
                        break;

                    // ============= 3. Registrar Cliente =============
                    case 3:
                        System.out.println("----- Registrar Cliente -----");
                        System.out.print("Ingrese el documento de identidad: ");
                        String docCliente = sc.nextLine();
                        System.out.print("Ingrese el nombre completo: ");
                        String nomCliente = sc.nextLine();
                        System.out.print("Ingrese el correo electrónico: ");
                        String correoCliente = sc.nextLine();
                        System.out.print("Ingrese el número de teléfono: ");
                        String telCliente = sc.nextLine();

                        Cliente cliente = new Cliente(docCliente, nomCliente, correoCliente, telCliente);

                        System.out.print("Ingrese el código de afiliación: ");
                        cliente.setCodigoAfiliacion(sc.nextLine());

                        cliente.setCategoria(pedirCategoriaCliente(sc));

                        if (empresa.registrarCliente(cliente)) {
                            System.out.println("Cliente registrado exitosamente.");
                        } else {
                            System.out.println("Ya existe un cliente con ese documento.");
                        }
                        break;

                    // ============= 4. Registrar Vendedor =============
                    case 4:
                        System.out.println("----- Registrar Vendedor -----");
                        System.out.print("Ingrese el documento de identidad: ");
                        String docVendedor = sc.nextLine();
                        System.out.print("Ingrese el nombre completo: ");
                        String nomVendedor = sc.nextLine();
                        System.out.print("Ingrese el correo electrónico: ");
                        String correoVendedor = sc.nextLine();
                        System.out.print("Ingrese el número de teléfono: ");
                        String telVendedor = sc.nextLine();

                        Vendedor vendedor = new Vendedor(docVendedor, nomVendedor, correoVendedor, telVendedor);

                        System.out.print("Ingrese el código de empleado: ");
                        vendedor.setCodigoEmpleado(sc.nextLine());
                        System.out.print("Ingrese el salario básico: ");
                        vendedor.setSalarioBasico(sc.nextDouble());
                        vendedor.setFechaContratacion(LocalDate.now());
                        sc.nextLine();

                        System.out.print("Ingrese el código de la sucursal a la que pertenece: ");
                        String codSucVendedor = sc.nextLine();
                        Sucursal sucVendedor = empresa.buscarSucursal(codSucVendedor);

                        if (sucVendedor == null) {
                            System.out.println("Error: La sucursal especificada no existe.");
                            break;
                        }

                        vendedor.setSucursal(sucVendedor);

                        if (empresa.registrarVendedor(vendedor)) {
                            sucVendedor.agregarVendedor(vendedor);
                            System.out.println("Vendedor registrado exitosamente en la sucursal.");
                        } else {
                            System.out.println("Ya existe un vendedor con ese documento.");
                        }
                        break;

                    // ============= 5. Registrar Inventario en Sucursal =============
                    case 5:
                        System.out.println("----- Registrar Existencias en Sucursal -----");
                        System.out.print("Ingrese el código de la sucursal: ");
                        Sucursal sucInventario = empresa.buscarSucursal(sc.nextLine());
                        if (sucInventario == null) {
                            System.out.println("Sucursal no encontrada.");
                            break;
                        }

                        System.out.print("Ingrese el código de la prenda (referencia): ");
                        Prenda prendaInv = empresa.buscarPrenda(sc.nextLine());
                        if (prendaInv == null) {
                            System.out.println("Prenda no encontrada en el catálogo.");
                            break;
                        }

                        System.out.print("Ingrese la talla (Ej: S, M, L, XL o número 35-44 para calzado): ");
                        String talla = sc.nextLine();
                        System.out.print("Ingrese el color: ");
                        String color = sc.nextLine();
                        System.out.print("Ingrese la cantidad inicial disponible: ");
                        int cantidad = sc.nextInt();
                        sc.nextLine();

                        // Validar si ya existe este inventario en la sucursal
                        if (sucInventario.buscarInventario(prendaInv, talla, color)) {
                            System.out.println("Error: Ya existe un registro de inventario para esta combinación de prenda, talla y color en esta sucursal.");
                        } else {
                            Inventario inventario = new Inventario(sucInventario, prendaInv, talla, color, cantidad);
                            sucInventario.registrarInventario(inventario);
                            prendaInv.agregarInventario(inventario);
                            System.out.println("Existencias registradas exitosamente.");
                        }
                        break;

                    // ============= 6. Consultar Prendas Disponibles =============
                    case 6:
                        System.out.println("----- Consultar Prendas Disponibles en Sucursal -----");
                        System.out.print("Ingrese el código de la sucursal: ");
                        Sucursal sucConsulta = empresa.buscarSucursal(sc.nextLine());
                        if (sucConsulta == null) {
                            System.out.println("Sucursal no encontrada.");
                            break;
                        }

                        ArrayList<Inventario> disponibles = sucConsulta.consultarPrendasDisponibles();
                        if (disponibles.isEmpty()) {
                            System.out.println("No hay prendas disponibles en esta sucursal.");
                        } else {
                            System.out.println("Prendas disponibles:");
                            for (Inventario inv : disponibles) {
                                System.out.println("- Prenda: " + inv.getPrenda().getNombre() +
                                        " | Talla: " + inv.getTalla() +
                                        " | Color: " + inv.getColor() +
                                        " | Cantidad: " + inv.getCantidadDisponible());
                            }
                        }
                        break;

                    // ============= 7. Registrar Venta =============
                    case 7:
                        sc.nextLine();
                        System.out.println("----- Registrar Venta -----");
                        System.out.print("Ingrese el código de la sucursal donde se efectúa la venta: ");
                        Sucursal sucVenta = empresa.buscarSucursal(sc.nextLine());
                        if (sucVenta == null) {
                            System.out.println("Sucursal no encontrada.");
                            break;
                        }

                        System.out.print("Ingrese el documento del cliente: ");
                        Cliente clienteVenta = empresa.buscarCliente(sc.nextLine());
                        if (clienteVenta == null) {
                            System.out.println("Cliente no encontrado.");
                            break;
                        }

                        System.out.print("Ingrese el documento del vendedor de la sucursal: ");
                        Vendedor vendedorVenta = empresa.buscarVendedor(sc.nextLine());
                        if (vendedorVenta == null || !vendedorVenta.getSucursal().equals(sucVenta)) {
                            System.out.println("Vendedor no encontrado o no pertenece a esta sucursal.");
                            break;
                        }

                        Venta venta = new Venta(sucVenta, clienteVenta, vendedorVenta);
                        System.out.print("Ingrese el código único de la venta: ");
                        venta.setCodigo(sc.nextLine());

                        // Agregar detalles
                        String continuar;
                        do {
                            System.out.print("Ingrese el código de la prenda a comprar: ");
                            Prenda pDetalle = empresa.buscarPrenda(sc.nextLine());
                            if (pDetalle == null) {
                                System.out.println("Prenda no encontrada.");
                            } else {
                                System.out.print("Ingrese la talla: ");
                                String tDetalle = sc.nextLine();
                                System.out.print("Ingrese el color: ");
                                String cDetalle = sc.nextLine();
                                System.out.print("Ingrese la cantidad deseada: ");
                                int cantDetalle = sc.nextInt();
                                sc.nextLine();

                                // Buscar el inventario exacto en la sucursal
                                Inventario invDetalle = null;
                                for (Inventario inv : sucVenta.getListaInventarios()) {
                                    if (inv.getPrenda().equals(pDetalle) && inv.getTalla().equalsIgnoreCase(tDetalle) && inv.getColor().equalsIgnoreCase(cDetalle)) {
                                        invDetalle = inv;
                                        break;
                                    }
                                }

                                if (invDetalle == null) {
                                    System.out.println("Error: El producto con esa talla y color no pertenece al inventario de esta sucursal.");
                                } else {
                                    DetalleVenta detalle = new DetalleVenta(cantDetalle, pDetalle.calcularPrecioConRecargo(), venta, invDetalle);
                                    venta.agregarDetalle(detalle);
                                    System.out.println("Detalle agregado temporalmente.");
                                }
                            }

                            System.out.print("¿Desea agregar otra prenda a la venta? (s/n): ");
                            continuar = sc.nextLine();
                        } while (continuar.equalsIgnoreCase("s"));

                        // Confirmar venta
                        if (venta.confirmar()) {
                            empresa.getVentas().add(venta);
                            vendedorVenta.agregarVenta(venta);
                            System.out.println("¡Venta confirmada exitosamente! Valor total a pagar: $" + venta.calcularTotal());
                        } else {
                            System.out.println("Error: La venta no pudo confirmarse porque las cantidades superan el inventario disponible o faltan existencias.");
                        }
                        break;

                    // ============= 8. Consultar Prendas Agotadas =============
                    case 8:
                        System.out.println("----- Prendas Agotadas en la Empresa -----");
                        ArrayList<Inventario> agotadas = empresa.consultarPrendasAgotadas();
                        if (agotadas.isEmpty()) {
                            System.out.println("No hay prendas agotadas actualmente.");
                        } else {
                            for (Inventario inv : agotadas) {
                                System.out.println("- Sucursal: " + inv.getSucursal().getNombre() +
                                        " | Prenda: " + inv.getPrenda().getNombre() +
                                        " | Talla: " + inv.getTalla() +
                                        " | Color: " + inv.getColor());
                            }
                        }
                        break;

                    // ============= 9. Consultar Ventas de un Vendedor =============
                    // ============= 9. Consultar Ventas de un Vendedor =============
                    case 9:
                        System.out.println("----- Ventas por Vendedor -----");
                        System.out.println("Ingrese el documento del vendedor: ");
                        String docVendDoc = sc.nextLine();

                        ArrayList<Venta> ventasVend = empresa.consultarVentasVendedor(docVendDoc);

                        if (ventasVend != null && !ventasVend.isEmpty()) {
                            for (Venta v : ventasVend) {
                                System.out.println("Venta Código: " + v.getCodigo() + " | Fecha: " + v.getFecha() + " | Total: $" + v.calcularTotal());
                            }
                        } else {
                            System.out.println("El vendedor no registra ventas o no existe.");
                        }
                        break;



                    // ============= 10. Consultar Compras de un Cliente =============
                    case 10:
                        System.out.println("----- Compras por Cliente -----");
                        System.out.print("Ingrese el documento del cliente: ");
                        String docCliDoc = sc.nextLine();
                        ArrayList<Venta> comprasCli = empresa.consultarComprasCliente(docCliDoc);
                        if (comprasCli.isEmpty()) {
                            System.out.println("El cliente no registra compras.");
                        } else {
                            for (Venta v : comprasCli) {
                                System.out.println("Compra Código: " + v.getCodigo() + " | Fecha: " + v.getFecha() + " | Sucursal: " + v.getSucursal().getNombre() + " | Total: $" + v.calcularTotal());
                            }
                        }
                        break;

                    // ============= 11. Calcular Ingresos de una Sucursal =============
                    case 11:
                        System.out.println("----- Calcular Ingresos Totales de Sucursal -----");
                        System.out.print("Ingrese el código de la sucursal: ");
                        String codSucIngresos = sc.nextLine();
                        double ingresos = empresa.calcularIngresosSucursal(codSucIngresos);
                        System.out.println("Ingresos totales generados por la sucursal: $" + ingresos);
                        break;

                    // ============= 12. Prenda Más Vendida en la Empresa =============
                    case 12:
                        System.out.println("----- Prenda con Mayor Cantidad de Unidades Vendidas -----");
                        Prenda topPrenda = empresa.prendaMasVendida();
                        if (topPrenda == null) {
                            System.out.println("Aún no hay registros de ventas en la empresa.");
                        } else {
                            System.out.println("La prenda más vendida es: " + topPrenda.getNombre() +
                                    " (Marca: " + topPrenda.getMarca() + ", Ref: " + topPrenda.getCodigo() + ")");
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo de la aplicación de ModaStyle...");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }

            } while (opcion != 0);

            sc.close();
        }
        private static CategoriaPrenda pedirCategoriaPrenda(Scanner sc) {
            System.out.println("Seleccione la categoría de la prenda:");
            System.out.println("1. Camiseta (Recargo 0%)");
            System.out.println("2. Pantalón (Recargo 0%)");
            System.out.println("3. Vestido (Recargo 10%)");
            System.out.println("4. Chaqueta (Recargo 15%)");
            System.out.println("5. Calzado (Recargo 5%)");
            int opcion = sc.nextInt();
            sc.nextLine();

            return switch (opcion) {
                case 1 -> CategoriaPrenda.CAMISETA;
                case 2 -> CategoriaPrenda.PANTALON;
                case 3 -> CategoriaPrenda.VESTIDO;
                case 4 -> CategoriaPrenda.CHAQUETA;
                case 5 -> CategoriaPrenda.CALZADO;
                default -> CategoriaPrenda.CAMISETA; // Opción por defecto
            };
        }

        private static CategoriaCliente pedirCategoriaCliente(Scanner sc) {
            System.out.println("Seleccione la categoría del cliente:");
            System.out.println("1. Bronce (Descuento 5%)");
            System.out.println("2. Plata (Descuento 10%)");
            System.out.println("3. Oro (Descuento 15%)");
            int opcion = sc.nextInt();
            sc.nextLine();

            return switch (opcion) {
                case 1 -> CategoriaCliente.BRONCE;
                case 2 -> CategoriaCliente.PLATA;
                case 3 -> CategoriaCliente.ORO;
                default -> CategoriaCliente.BRONCE;
            };
        }
     }

