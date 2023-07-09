interface RentalMessage {
    void displayThankYouMessage(String namaPenyewa);
}

class GDsRentcar implements RentalMessage {
    // Implementasi method dari RentalMessage
    public void displayThankYouMessage(String namaPenyewa) {
        System.out.println("Terima kasih, " + namaPenyewa + ", sudah menyewa mobil di GDs Rentcar!");
        System.out.println("Kami harap Anda puas dengan layanan kami.");
    }
}