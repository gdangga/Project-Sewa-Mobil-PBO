import java.util.ArrayList;

class Garasi {
  private ArrayList<Mobil> mobils = new ArrayList<>();
  private ArrayList<Customer> customers = new ArrayList<>();

  public void addCustomer(Customer customer) {
    for (Customer c : this.getCustomers()) {
      if (c.id.equals(customer.id)) {
        System.out.println("Id member ini sudah ada: " + customer.id);
        return;
      } 
    }
    this.getCustomers().add(customer);
    System.out.println("Member dengan Id " +customer.id+ " berhasil ditambahkan");
  }
    public ArrayList<Customer> getCustomers() {

    return customers;
  }
  
  public void setCustomers(ArrayList<Customer> customers) {

    this.customers = customers;
  }

  public void tambahMobil(Mobil mobil) {
    for (Mobil b : this.mobils) {
      if (b.getId().equals(mobil.getId())) {
        System.out.println("Id buku ini sudah ada: " + mobil.getId());
        return;
      }
        
        
    }
    System.out.println("Buku dengan ID" + mobil.getId() + " berjudul " + mobil.getTitle() + " berhasil ditambahkan");
        this.mobils.add(mobil);
  }

  public void setMobils(ArrayList<Mobil> mobils) {

    this.mobils = mobils;
  }

  public ArrayList<Mobil> getMobils() {

    return mobils;
  }


  public void giveMobil(String mobilId, String customerId) {
    Mobil mobil = this.getMobilById(mobilId);
    this.getMobils().remove(mobil);
    Customer customer = this.getCustomersById(customerId);

    int customerIndex = this.getCustomerIndex(customer);
    
    this.customers.get(customerIndex).borrowedMobils.add(mobil);

    try {
      System.out.println("Buku dengan ID " + mobilId + " berhasil dipinjam oleh member dengan ID " + customerId);
    } catch (Exception e) {
      System.out.println("Terjadi kesalahan saat mencoba meminjam buku: ");
    }
  }

  public void receiveMobils(String mobilId, String customerId) {
    Customer customer = this.getCustomersById(customerId);
    int customerIndex = this.getCustomerIndex(customer);

    Mobil mobil = this.customers.get(customerIndex).getMobilById(mobilId);

    try {
      if (mobil == null) {
        throw new Exception("Buku dengan ID " + mobilId + " belum pernah dipinjam.");
      }
  
      this.getMobils().add(mobil);
      this.getCustomers().get(customerIndex).borrowedMobils.remove(mobil);
      System.out.println("Buku dengan ID " + mobilId + " berhasil dikembalikan oleh member dengan ID " + customerId);
    } catch (Exception e) {
      System.out.println("Terjadi kesalahan saat mencoba mengembalikan buku: " + e.getMessage());
    }
  }


  private int getCustomerIndex(Customer customer) {
    return this.getCustomers().indexOf(customer);
  }

  private Customer getCustomersById(String id) {
    for (Customer customer : this.getCustomers()) {
      if (customer.id.equals(id)) {
        return customer;
      }
    }
    return null;
  }

  private Mobil getMobilById(String id) {
    for (Mobil mobil : this.getMobils()) {
      if (mobil.getId().equals(id)) {
        return mobil;
      }
    }
    return null;
  }
}