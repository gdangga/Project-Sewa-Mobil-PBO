import java.util.ArrayList;

abstract class DaftarCs {
  public String id;
  public String name;
  public ArrayList<Mobil> borrowedMobils = new ArrayList<Mobil>();

  public void terimaMobil(Mobil mobil) {
    this.borrowedMobils.add(mobil);
  }

  public void giveMobil(Mobil mobil) {
    this.borrowedMobils.remove(mobil);
  }

  public Mobil getMobilById(String id) {
    for (Mobil mobil : this.borrowedMobils) {
      if (mobil.getId().equals(id)) {
        return mobil;
      }
    }
    return null;
  }

  public abstract String getId();
}

class Customer extends DaftarCs {
  public Customer() {
  }

  @Override
  public String getId() {
    return this.id;
  }
}




