import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Mobil {
    private String nama;
    private boolean disewa;
    private String penyewa;

    public Mobil(String nama) {
        this.nama = nama;
        this.disewa = false;
        this.penyewa = "";
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
}

class ProgramSewaMobil {
    private ArrayList<Mobil> daftarMobil;
    private HashMap<String, ArrayList<String>> riwayatPenyewa;
    private String passwordAdmin;

    public ProgramSewaMobil(String passwordAdmin) {
        daftarMobil = new ArrayList<>();
        riwayatPenyewa = new HashMap<>();
        this.passwordAdmin = passwordAdmin;
    }

    public void tambahMobil(Mobil mobil) {
        daftarMobil.add(mobil);
    }

    public void sewaMobil(int indeks, String penyewa) {
        if (indeks >= 0 && indeks < daftarMobil.size()) {
            Mobil mobil = daftarMobil.get(indeks);
            if (!mobil.isDisewa()) {
                mobil.setDisewa(true);
                mobil.setPenyewa(penyewa);
                System.out.println("Mobil " + mobil.getNama() + " berhasil disewa oleh " + penyewa + ".");
                tambahkanRiwayatPenyewa(penyewa, mobil.getNama());
            } else {
                System.out.println("Mobil " + mobil.getNama() + " sudah disewa.");
            }
        } else {
            System.out.println("Indeks mobil tidak valid.");
        }
    }

    public void kembalikanMobil(int indeks) {
        if (indeks >= 0 && indeks < daftarMobil.size()) {
            Mobil mobil = daftarMobil.get(indeks);
            if (mobil.isDisewa()) {
                mobil.setDisewa(false);
                String penyewa = mobil.getPenyewa();
                mobil.setPenyewa("");
                System.out.println("Mobil " + mobil.getNama() + " berhasil dikembalikan oleh " + penyewa + ".");
            } else {
                System.out.println("Mobil " + mobil.getNama() + " tidak sedang disewa.");
            }
        } else {
            System.out.println("Indeks mobil tidak valid.");
        }
    }

    public void tampilkanDaftarMobil() {
        System.out.println("Daftar Mobil:");
        for (int i = 0; i < daftarMobil.size(); i++) {
            Mobil mobil = daftarMobil.get(i);
            System.out.print(i + ". " + mobil.getNama());
            if (mobil.isDisewa()) {
                System.out.println(" (Disewa oleh " + mobil.getPenyewa() + ")");
            } else {
                System.out.println(" (Tersedia)");
            }
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

    public boolean isValidPassword(String password) {
        return password.equals(passwordAdmin);
    }
}

public class Main {
    public static void main(String[] args) {
        ProgramSewaMobil program = new ProgramSewaMobil("admin123");

        // Tambahkan mobil ke daftar
        program.tambahMobil(new Mobil("Avanza"));
        program.tambahMobil(new Mobil("Innova"));
        program.tambahMobil(new Mobil("Ertiga"));

        Scanner scanner = new Scanner(System.in);
        int pilihan;
        boolean isAdminMode = false;

        do {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Tampilkan Daftar Mobil");
            System.out.println("2. Sewa Mobil");
            System.out.println("3. Kembalikan Mobil");
            System.out.println("4. Tampilkan Riwayat Penyewa");
            System.out.println("5. Mode Admin");
            System.out.println("6. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    program.tampilkanDaftarMobil();
                    break;
                case 2:
                    if (isAdminMode) {
                        System.out.println("Anda berada dalam Mode Admin. Tidak dapat melakukan aksi ini.");
                        break;
                    }
                    System.out.print("Masukkan indeks mobil yang ingin disewa: ");
                    int indeksSewa = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan newline character dari input sebelumnya
                    System.out.print("Masukkan nama penyewa: ");
                    String penyewa = scanner.nextLine();
                    program.sewaMobil(indeksSewa, penyewa);
                    break;
                case 3:
                    if (isAdminMode) {
                        System.out.println("Anda berada dalam Mode Admin. Tidak dapat melakukan aksi ini.");
                        break;
                    }
                    System.out.print("Masukkan indeks mobil yang ingin dikembalikan: ");
                    int indeksKembali = scanner.nextInt();
                    program.kembalikanMobil(indeksKembali);
                    break;
                case 4:
                    program.tampilkanRiwayatPenyewa();
                    break;
                case 5:
                    System.out.print("Masukkan password Admin: ");
                    String password = scanner.next();
                    if (program.isValidPassword(password)) {
                        isAdminMode = true;
                        System.out.println("Anda telah masuk ke Mode Admin.");
                    } else {
                        System.out.println("Password Admin salah. Mode Admin tidak dapat diakses.");
                    }
                    break;
                case 6:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

            if (isAdminMode) {
                if (pilihan == 5) {
                    do {
                        System.out.println("\n===== Mode Admin =====");
                        System.out.println("1. Tambah Mobil");
                        System.out.println("2. Kembali ke Menu Utama");
                        System.out.print("Pilihan Anda: ");
                        pilihan = scanner.nextInt();

                        switch (pilihan) {
                            case 1:
                                System.out.print("Masukkan nama mobil yang ingin ditambahkan: ");
                                scanner.nextLine(); // Membersihkan newline character dari input sebelumnya
                                String namaMobil = scanner.nextLine();
                                program.tambahMobil(new Mobil(namaMobil));
                                System.out.println("Mobil " + namaMobil + " berhasil ditambahkan.");
                                break;
                            case 2:
                                isAdminMode = false;
                                System.out.println("Keluar dari Mode Admin.");
                                break;
                            default:
                                System.out.println("Pilihan tidak valid.");
                                break;
                        }
                    } while (pilihan != 2);
                } else {
                    System.out.println("Anda harus keluar dari Mode Admin terlebih dahulu untuk mengakses menu lain.");
                    isAdminMode = false;
                }
            }
        } while (pilihan != 6);
    }
}
