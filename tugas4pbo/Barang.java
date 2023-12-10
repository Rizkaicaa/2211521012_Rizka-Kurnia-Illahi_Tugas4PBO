public class Barang {
    private String namaBarang;
    private String kodeBarang;
    private double harga;
    private int jumlah;

    public Barang(String kodeBarang, String namaBarang, double harga, int jumlah) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public double getHarga() {
        return harga;
    }

    public int getJumlah() {
        return jumlah;
    }
}
