import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Garasi garasi = new Garasi("admin321");

        garasi.tambahMobil(new Mobil("Toyota Avanza", 250000) {
            public String getTipe() {
                return "Mpv";
            }
        });
        garasi.tambahMobil(new Mobil("Toyota Innova", 350000) {
            public String getTipe() {
                return "MPV";
            }
        });
        garasi.tambahMobil(new Mobil("Suzuki Ertiga", 250000) {
            public String getTipe() {
                return "MPV";
            }
        });
        garasi.tambahMobil(new SUV("Toyota Fortuner", 500000, "4x4"));
        garasi.tambahMobil(new SUV("Mitsubishi Pajero Sport", 600000, "4x4"));

        Scanner scanner = new Scanner(System.in);
        int pilihan;
        boolean isAdminMode = false;

        do {
            garasi.tampilkanDaftarMobil();
            System.out.println("\n===== Menu =====");
            System.out.println("1. Sewa Mobil");
            System.out.println("2. Kembalikan Mobil");
            System.out.print("Pilihan Anda: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {

                case 1:
                    if (isAdminMode) {
                        System.out.println("Anda berada dalam Mode Admin. Tidak dapat melakukan aksi ini.");
                        break;
                    }
                    System.out.print("Masukkan indeks mobil yang ingin disewa: ");
                    int indeksSewa = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan newline character dari input sebelumnya
                    System.out.print("Masukkan nama penyewa: ");
                    String penyewa = scanner.nextLine();
                    System.out.print("Masukkan durasi sewa (dalam hari): ");
                    int durasi = scanner.nextInt();
                    garasi.sewaMobil(indeksSewa, penyewa, durasi);
                    break;
                case 2:
                    if (isAdminMode) {
                        System.out.println("Anda berada dalam Mode Admin. Tidak dapat melakukan aksi ini.");
                        break;
                    }
                    System.out.print("Masukkan indeks mobil yang ingin dikembalikan: ");
                    int indeksKembali = scanner.nextInt();
                    garasi.kembalikanMobil(indeksKembali);
                    break;
                case 3:
                    System.out.print("Masukkan password Admin: ");
                    String password = scanner.next();
                    if (garasi.isValidPassword(password)) {
                        System.out.println("Mode Admin diakses.");
                        isAdminMode = true;
                        do {
                            System.out.println("\n===== Mode Admin =====");
                            System.out.println("1. Tambah Mobil");
                            System.out.println("2. Kembali ke Menu Utama");
                            System.out.println("3. Tampilkan Riwayat Penyewa");
                            System.out.print("Pilihan Anda: ");
                            pilihan = scanner.nextInt();

                            switch (pilihan) {
                                case 1:
                                    System.out.print("Masukkan nama mobil yang ingin ditambahkan: ");
                                    scanner.nextLine(); // Membersihkan newline character dari input sebelumnya
                                    String namaMobil = scanner.nextLine();
                                    System.out.print("Masukkan harga sewa per hari: ");
                                    double hargaMobil = scanner.nextDouble();
                                    garasi.tambahMobil(new Mobil(namaMobil, hargaMobil) {
                                        @Override
                                        public String getTipe() {
                                            return null;
                                        }
                                    });
                                    System.out.println("Mobil " + namaMobil + " berhasil ditambahkan.");
                                    break;
                                case 2:
                                    isAdminMode = false;
                                    System.out.println("Keluar dari Mode Admin.");
                                    break;
                                case 3:
                                    garasi.tampilkanRiwayatPenyewa();
                                    break;
                                default:
                                    System.out.println("Pilihan tidak valid.");
                                    break;
                            }
                        } while (pilihan != 2);
                    } else {
                        System.out.println("Password Admin salah. Mode Admin tidak dapat diakses.");
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

            if (isAdminMode) {
                if (pilihan == 5) {
                    System.out.println("Anda harus keluar dari Mode Admin terlebih dahulu untuk mengakses menu lain.");
                    isAdminMode = false;
                }
            }
        } while (pilihan != 5);
    }
}
