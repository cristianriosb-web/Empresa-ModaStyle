public enum CategoriaCliente {
    BRONCE(0.05),
    PLATA(0.10),
    ORO(0.15);

    private double descuento;

    CategoriaCliente(double recargo) {
    this.descuento=descuento;
    }
    public double getDescuento() {
        return descuento;
    }

}
