import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Garasi {
    private ArrayList<Mobil> daftarMobil;
    private HashMap<String, ArrayList<String>> riwayatPenyewa;
    private String passwordAdmin;

    public Garasi(String passwordAdmin) {
        daftarMobil = new ArrayList<>();
        riwayatPenyewa = new HashMap<>();
        this.passwordAdmin = passwordAdmin;
    }

    public void tambahMobil(Mobil mobil) {

        daftarMobil.add(mobil);
    }

    public void tambahMobil(SUV suv) {
        daftarMobil.add(suv);
    } 

    public void sewaMobil(int indeks, String penyewa, int durasi) {
        if (indeks >= 1 && indeks <= daftarMobil.size()) {
            Mobil mobil = daftarMobil.get(indeks - 1);
            if (!mobil.isDisewa()) {
                mobil.setDisewa(true);
                mobil.setPenyewa(penyewa);
                double totalHarga = mobil.getHarga() * durasi;
                System.out.println("Mobil " + mobil.getNama() + " berhasil disewa oleh " + penyewa);
                System.out.println("Durasi: " + durasi + " hari");
                System.out.println("Total Harga: " + totalHarga);
                tambahkanRiwayatPenyewa(penyewa, mobil.getNama());
                GDsRentcar rentcar = new GDsRentcar();
                rentcar.displayThankYouMessage(penyewa);
            } else {
                System.out.println("Mobil " + mobil.getNama() + " sudah disewa.");
            }
        } else {
            System.out.println("Indeks mobil tidak valid.");
        }
    }

    public void kembalikanMobil(int indeks) {
        try {
            if (indeks >= 1 && indeks <= daftarMobil.size()) {
                Mobil mobil = daftarMobil.get(indeks - 1);
                if (mobil.isDisewa()) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Masukkan nama penyewa: ");
                    String namaPenyewa = scanner.nextLine();

                    if (namaPenyewa.equalsIgnoreCase(mobil.getPenyewa())) {
                        mobil.setDisewa(false);
                        mobil.setPenyewa("");
                        System.out.println("Mobil " + mobil.getNama() + " berhasil dikembalikan oleh " + namaPenyewa + " Terimakasih.");
                    } else {
                        System.out.println("Nama penyewa yang dimasukkan tidak sesuai. Mobil " + mobil.getNama() + " tidak dapat dikembalikan.");
                    }
                } else {
                    System.out.println("Mobil " + mobil.getNama() + " tidak sedang disewa.");
                }
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }


    public void tampilkanDaftarMobil() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Daftar Mobil:");
        for (int i = 0; i < daftarMobil.size(); i++) {
            Mobil mobil = daftarMobil.get(i);
            String status = mobil.isDisewa() ? "Disewa" : "Tersedia";
            int index = i + 1;
            System.out.println(index + ". " + mobil + " - Status: " + status);
        }
    }


    private void tambahkanRiwayatPenyewa(String penyewa, String mobil) {
        if (riwayatPenyewa.containsKey(penyewa)) {
            ArrayList<String> riwayat = riwayatPenyewa.get(penyewa);
            riwayat.add(mobil);
        } else {
            ArrayList<String> riwayat = new ArrayList<>();
            riwayat.add(mobil);
            riwayatPenyewa.put(penyewa, riwayat);
        }
    }

    public void tampilkanRiwayatPenyewa() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Riwayat Penyewa:");
        for (String penyewa : riwayatPenyewa.keySet()) {
            System.out.println("Penyewa: " + penyewa);
            ArrayList<String> riwayat = riwayatPenyewa.get(penyewa);
            System.out.println("Mobil yang disewa:");
            for (String mobil : riwayat) {
                System.out.println("- " + mobil);
            }
            System.out.println();
        }
    }

    public void hapusMobil(int indeks) {
        if (indeks >= 1 && indeks <= daftarMobil.size()) {
            Mobil mobil = daftarMobil.get(indeks - 1);
            if (!mobil.isDisewa()) {
                daftarMobil.remove(indeks - 1);
                System.out.println("Mobil " + mobil.getNama() + " berhasil dihapus.");
            } else {
                System.out.println("Mobil " + mobil.getNama() + " sedang disewa. Tidak dapat menghapus mobil yang sedang disewa.");
            }
        } else {
            System.out.println("Indeks mobil tidak valid.");
        }
    }


    public boolean isValidPassword(String password) {
        return password.equals(passwordAdmin);
    }
}