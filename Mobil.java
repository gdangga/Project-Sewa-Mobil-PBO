class Mobil {
    private String nama;
    private boolean disewa;
    private String penyewa;
    private double harga;

    public Mobil(String nama, double harga) {
        this.nama = nama;
        this.disewa = false;
        this.penyewa = "";
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public boolean isDisewa() {
        return disewa;
    }

    public void setDisewa(boolean disewa) {
        this.disewa = disewa;
    }

    public String getPenyewa() {
        return penyewa;
    }

    public void setPenyewa(String penyewa) {
        this.penyewa = penyewa;
    }

    public double getHarga() {
        return harga;
    }
}