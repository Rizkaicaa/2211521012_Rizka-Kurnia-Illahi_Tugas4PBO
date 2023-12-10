import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App {

    private static Login login = new Login();
    private static boolean isLoggedIn = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mengecek login sebelum memulai transaksi
        if (!doLogin(scanner)) {
            scanner.close();
            return;
        }

        boolean ulangTransaksi = true;
        while (ulangTransaksi) {
            // Input Data Transaksi
            System.out.println("\n----Input Data Transaksi----");
            try {
                System.out.print("No. Faktur\t: ");
                String noFaktur = scanner.nextLine();

                System.out.print("Nama Pelanggan\t: ");
                String namaPelanggan = scanner.nextLine();

                System.out.print("No. HP\t\t: ");
                String noHpPelanggan = scanner.nextLine();

                System.out.print("Alamat\t\t: ");
                String alamatPelanggan = scanner.nextLine();

                Faktur faktur = new Faktur(noFaktur, namaPelanggan);

                // Input Nama Kasir
                System.out.print("Nama Kasir\t: ");
                String namaKasir = scanner.nextLine();
                faktur.setNamaKasir(namaKasir);

                // Input Barang
                boolean tambahBarang = true;
                while (tambahBarang) {
                    System.out.print("Kode Barang\t: ");
                    String kodeBarang = scanner.nextLine();

                    System.out.print("Nama Barang\t: ");
                    String namaBarang = scanner.nextLine();

                    System.out.print("Harga Barang\t: ");
                    double hargaBarang = scanner.nextDouble();

                    System.out.print("Jumlah Barang\t: ");
                    int jumlahBarang = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan buffer newline

                    Barang barang = new Barang(kodeBarang, namaBarang, hargaBarang, jumlahBarang);
                    HitungTotalBayar hitungTotalBayar = new HitungTotalBayar();

                    faktur.tambahBarang(barang);
                    faktur.setTotalBayar(faktur.getTotalBayar() + hitungTotalBayar.calculateTotal(barang));

                    // Apabila terdapat lebih dari satu barang
                    System.out.print("Tambah barang?(y/n): ");
                    String tambahLagi = scanner.nextLine().toLowerCase();
                    tambahBarang = tambahLagi.equals("y");
                }

                // Menampilkan faktur belanja
                System.out.println("\n========= R3 SUPERMARKET =========");
                System.out.println("Tanggal \t: " + getFormattedTanggal());
                System.out.println("Waktu   \t: " + getFormattedWaktu());
                System.out.println("No. Faktur\t: " + noFaktur);
                System.out.println("====================================");
                System.out.println("         DATA PELANGGAN");
                System.out.println("------------------------------------");
                System.out.println("Nama Pelanggan \t: " + namaPelanggan.toUpperCase());
                System.out.println("No. HP         \t: " + noHpPelanggan); // Tambahkan informasi No. HP
                System.out.println("Alamat         \t: " + alamatPelanggan); // Tambahkan informasi Alamat
                System.out.println("++++++++++++++++++++++++++++++++++++");
                System.out.println("      DATA PEMBELIAN BARANG");
                System.out.println("------------------------------------");
                faktur.displayFaktur();
                // Tampilkan nama kasir
                System.out.println("++++++++++++++++++++++++++++++++++++");
                System.out.println("Kasir\t\t: " + namaKasir.toUpperCase());
                System.out.println("Terima kasih! Silahkan datang kembali.");
            } catch (Exception e) {
                System.out.println("Terjadi Kesalahan Pada Input: " + e.getMessage());
                System.out.println("\n");
            }

            // untuk lanjut ke transaksi pembelian berikutnya
            System.out.print("\nLakukan transaksi pembeli berikutnya? (y/n): ");
            String ulangiTransaksi = scanner.nextLine().toLowerCase();
            ulangTransaksi = ulangiTransaksi.equals("y");
        }
        scanner.close();
    }

    private static boolean doLogin(Scanner scanner) {
        System.out.println("-----------Login-----------");
        System.out.print("Username\t: ");
        String username = scanner.nextLine();

        System.out.print("Password\t: ");
        String password = scanner.nextLine();

        System.out.print("Captcha\t\t: " + login.getCurrentCaptcha() + "\nInput Captcha\t: ");
        String enteredCaptcha = scanner.nextLine();

        isLoggedIn = login.authenticate(username, password, enteredCaptcha);
        if (isLoggedIn) {
            System.out.println("Login successful!\n");
        } else {
            System.out.println("Login failed. Username, password, or captcha incorrect.\n");
        }

        return isLoggedIn;
    }

    // Metode untuk mendapatkan tanggal dan waktu saat ini dengan format "EEE,
    // dd/MM/yyyy"
    private static String getFormattedTanggal() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEEEEE, dd/MM/yyyy");
        return dateFormat.format(date);
    }

    // Metode untuk mendapatkan waktu saat ini dengan format "hh:mm:ss z"
    private static String getFormattedWaktu() {
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss z");
        return timeFormat.format(date);
    }
}
