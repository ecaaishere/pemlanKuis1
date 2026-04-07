import java.util.Scanner;

public class Moo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String nama;
        while (true) {
            nama = input.nextLine().trim();
            if (nama.matches("[a-zA-Z]+")) break;
            System.out.println("Mooo! Nama sapi harus pakai huruf, bukan angka atau simbol!");
        }
        
        int berat;
        while (true) {
            try {
                berat = Integer.parseInt(input.nextLine().trim());
                if (berat >= 1 && berat <= 80) break;
            } catch (Exception e) {}
            System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
        }
        
        String layanan;
        int hargaPerKg = 0;
        while (true) {
            layanan = input.nextLine().trim().toLowerCase();
            
            if (layanan.equals("spa")) {
                hargaPerKg = 8000;
                break;
            } else if (layanan.equals("potong_kuku")) {
                hargaPerKg = 6000;
                break;
            } else if (layanan.equals("grooming")) {
                hargaPerKg = 10000;
                break;
            } else {
                System.out.println("Pilih spa, potong_kuku, atau grooming! Sapi kamu mau dirawat apa, sih?");
            }
        }
        
        String kelas;
        while (true) {
            kelas = input.nextLine().trim().toLowerCase();
            if (kelas.equals("reguler") || kelas.equals("vip")) break;
            System.out.println("Pilih reguler atau vip! Sapi kamu mau treatment sultan atau biasa aja?");
        }
        
        boolean isSpecial = nama.equals("Moo") || nama.equals("Mooo") || nama.equals("Moooo");
        double biayaDasar = berat * hargaPerKg;
        double diskonBerat = (berat > 30) ? biayaDasar * 0.1 : 0;
        double vip = kelas.equals("vip") ? biayaDasar * 0.2 : 0;
        double subtotal = biayaDasar - diskonBerat + vip;
        double pajak = subtotal * 0.08;  
        double total = subtotal + pajak; 

        if (isSpecial) {
            total = 0;
        }

        System.out.println("========== NOTA KLINIK SAPI ==========");
        System.out.println("Nama Sapi: " + nama);
        System.out.println("Berat: " + berat + " kg");
        
        String layananDisplay = "";
        switch(layanan) {
            case "spa": layananDisplay = "spa"; break;
            case "potong_kuku": layananDisplay = "potong_kuku"; break;
            case "grooming": layananDisplay = "grooming"; break;
        }
        System.out.println("Jenis Layanan: " + layananDisplay);
        
        String kelasDisplay = kelas.equals("reguler") ? "reguler" : "vip";
        System.out.println("Kelas: " + kelasDisplay);
        
        System.out.printf("Biaya Dasar: Rp %.1f\n", biayaDasar);
        System.out.printf("Diskon: Rp %.1f\n", diskonBerat);
        if (kelas.equals("vip")) {
            System.out.printf("Biaya Tambahan VIP: Rp %.1f\n", vip);
        } else {
            System.out.println("Biaya Tambahan VIP: Rp 0.0");
        }

        System.out.printf("Subtotal: Rp %.1f\n", subtotal);
        System.out.printf("Pajak: Rp %.1f\n", pajak);
        System.out.printf("Total Biaya: Rp %.1f\n", total);
        System.out.println("=============================================");
        
        if (isSpecial) {
            System.out.println("Terima kasih, " + nama + " ! Sapi spesial memang beda perlakuan~");
        } else {
            System.out.println("Terima kasih, " + nama + " ! Semoga sapinya makin glow up.");
        }
        
    }
}