interface RentalMessage {
    void displayThankYouMessage(String namaPenyewa);
}

abstract class Rent {
    public abstract void harapPuasDenganLayananKami();
}

class GDsRentcar extends Rent implements RentalMessage {
    public void displayThankYouMessage(String namaPenyewa) {
        System.out.println("Terima kasih, " + namaPenyewa + ", sudah menyewa mobil di GDs Rentcar!");
        harapPuasDenganLayananKami();
    }
    public void harapPuasDenganLayananKami() {
        // Implementasi metode harapPuasDenganLayananKami() di sini
        System.out.println("Kami harap Anda puas dengan layanan kami.");
    }
}
