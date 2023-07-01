import java.util.InputMismatchException;
import java.util.Scanner;

class Main {

  static Scanner scan = new Scanner(System.in);
  static Garasi garasi = new Garasi();
  static boolean isAdminMode = false;
  static final String adminPassword = "admin123";
 
  public static void main(String[] args) {
    initLibraryData();

    String isContinue = "y";

    while (isContinue.equals("y")) {
      showMenu();
      int selectedMenu = chooseMenu();

      if (selectedMenu == 3) {
        if (isAdminMode) {
          isAdminMode = false;
        } else {
          System.out.print("Enter admin password: ");
          String password = scan.next();
          if (password.equals(adminPassword)) {
            isAdminMode = true;
            System.out.println("Logged in as admin.");
          } else {
            System.out.println("Password admin salah.");
          }
        }
      } else if (selectedMenu == 4 && isAdminMode) {
        addBookToGarage();
      } else if (selectedMenu == 1) {
        // Sewa Mobil
        rentaCar();
      } else if (selectedMenu == 2) {
        // Kembalikan Mobil
        returnCar();
      } else if (selectedMenu == 5 && isAdminMode) {
        // Logout admin
        isAdminMode = false;
        System.out.println("Logged out from admin mode.");
      } else {
        System.out.println("Input salah, masukkan angka dari 1 sampai 4");
      }

      System.out.print("Continue? ");
      isContinue = scan.next();
    }
  }

  public static void initLibraryData() {
    Mobil mobil1 = new Mobil();
    mobil1.setId("1");
    mobil1.setTitle("Toyota Avanza");
    mobil1.setHarga(300000);

    Mobil mobil2 = new Mobil();
    mobil2.setId("2");
    mobil2.setTitle("Suzuki Apv");
    mobil2.setHarga(300000);

    Mobil mobil3 = new Mobil();
    mobil3.setId("3");
    mobil3.setTitle("Daihatsu Xenia");
    mobil3.setHarga(300000);

    garasi.getMobils().add(mobil1);
    garasi.getMobils().add(mobil2);
    garasi.getMobils().add(mobil3);
  }

  public static void showMenu() {
    for (Mobil book : garasi.getMobils()) {
      System.out.println(book.getId() + " " + book.getTitle());
    }
    System.out.println("================================");
    System.out.println("1. Sewa Mobil");
    System.out.println("2. Kembalikan Mobil");
    System.out.println("3. Admin Mode");
    if (isAdminMode) {
      System.out.println("4. Tambahkan Mobil ke Garasi");
      System.out.println("5. Logout admin");

    }
    System.out.println("================================");
  }

  public static int chooseMenu() {
    int pilihan = 0;
    boolean validInput = false;

    while (!validInput) {
      try {
        System.out.print("Choose menu: ");
        pilihan = scan.nextInt();

        if (isAdminMode) {
          if (pilihan < 1 || pilihan > 5) {
            throw new InputMismatchException();
          }
        } else {
          if (pilihan < 1 || pilihan > 3) {
            throw new InputMismatchException();
          }
        }

        validInput = true;
      } catch (InputMismatchException e) {
        System.out.println("Maaf, masukkan angka sesuai pilihan menu.");
        scan.nextLine();
      }
    }

    return pilihan;
  }


  public static void rentaCar() {
    Customer customer = new Customer();

    System.out.print("id : ");
    customer.id = scan.next();

    System.out.print("nama : ");
    scan.nextLine();
    customer.name = scan.nextLine();

    System.out.print("Nomor Telepon : ");
    scan.nextLine();
    customer.name = scan.nextLine();

    garasi.addCustomer(customer);

    System.out.print("id mobil : ");
    String mobilId = scan.next();

    String customerId = customer.id;

    garasi.giveMobil(mobilId, customerId);
  }

  public static void returnCar() {
    System.out.print("id member : ");
    String customerId = scan.next();

    System.out.print("id book : ");
    String mobilId = scan.next();

    garasi.receiveMobils(mobilId, customerId);
  }

  public static void addBookToGarage() {
    Mobil mobil = new Mobil();

    System.out.print("id: ");
    mobil.setId(scan.next());

    System.out.print("title: ");
    scan.nextLine();
    mobil.setTitle(scan.nextLine());

    garasi.getMobils().add(mobil);

    System.out.println("Mobil berhasil ditambahkan ke garasi.");
  }
}
