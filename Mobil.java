class Mobil {
    private String nama;
    private boolean disewa;
    private String penyewa;
    private double harga;
    private String tipe;


    public Mobil(String nama, double harga, String tipe) {
        this.nama = nama;
        this.disewa = false;
        this.penyewa = "";
        this.harga = harga;
        this.tipe = tipe;
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

    public String getTipe() {
        return tipe;
    }
    @Override
    public String toString() {
        return getNama() + " - Tipe: " + getTipe() + " - Harga per Hari: " + getHarga();
    }

    public Mobil(){
        this.nama = "Toyota Yaris";
        this.disewa = false;
        this.penyewa = "";
        this.harga = 400000;
        this.tipe = "City Car";
    }
}

class SUV extends Mobil {
    private String sistemPenggerak;

    public SUV(String nama, double harga, String tipe, String sistemPenggerak) {
        super(nama, harga, tipe);
        this.sistemPenggerak = sistemPenggerak;
    }

    public String getSistemPenggerak() {
        return sistemPenggerak;
    }
    @Override
    public String toString() {
        return super.toString() + " - Sistem Penggerak: " + getSistemPenggerak();
    }
}
