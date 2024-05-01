import java.util.LinkedList;
import java.util.Queue;

// TreeNode sınıfı, ağaçtaki düğümleri temsil eder
class TreeNode {
    String data;
    TreeNode left, right;
    int height;

    public TreeNode(String data) {
        this.data = data;
        this.height = 1;
    }
}

// AVLTree sınıfı, AVL ağacını temsil eder
class AVLTree {
    TreeNode root;

    // Ağacın yüksekliğini döndüren yardımcı metot
    int height(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Yeni yüksekliği hesaplayan yardımcı metot
    int newHeight(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Denge faktörünü hesaplayan yardımcı metot
    int balanceFactor(TreeNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // Düğümleri sağa döndüren yardımcı metot
    TreeNode rotateRight(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = newHeight(y);
        x.height = newHeight(x);

        return x;
    }

    // Düğümleri sola döndüren yardımcı metot
    TreeNode rotateLeft(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = newHeight(x);
        y.height = newHeight(y);

        return y;
    }

    // AVL ağacına düğüm ekleme metodu
    TreeNode insert(TreeNode node, String data) {
        if (node == null)
            return new TreeNode(data);

        if (data.compareTo(node.data) < 0)
            node.left = insert(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = insert(node.right, data);
        else
            return node; // Aynı düğümü birden fazla eklemeyi önle

        // Yükseklik güncellemesi
        node.height = newHeight(node);

        // Dengeliğin kontrolü
        int balance = balanceFactor(node);

        // Sol-sol durumu
        if (balance > 1 && data.compareTo(node.left.data) < 0)
            return rotateRight(node);

        // Sağ-sağ durumu
        if (balance < -1 && data.compareTo(node.right.data) > 0)
            return rotateLeft(node);

        // Sol-sağ durumu
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Sağ-sol durumu
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Ağacı dolaşarak yemek malzemelerini  yazdıran metot (inorder)
    void printIngredients(TreeNode node) {
        if (node != null) {
            printIngredients(node.left);
            System.out.println(node.data);
            printIngredients(node.right);
        }
    }

    // Yemek malzemelerini yazdıran metot
    void printIngredients() {
        printIngredients(root);
    }
}

// Tarifleri aşama aşama yazdıran metotların olduğu sınıf
class PrintSteps {

    // Karnıyarık tarifini yazdıran metot
    public static void karnıyarıkPrintSteps() {
        Queue<String> karnıyarıkRecipeSteps = new LinkedList<>();

        // Karnıyarık tarifini kuyruğa ekliyoruz
        karnıyarıkRecipeSteps.offer("Bol suda yıkadığınız patlıcanları, pijamalı şekilde soyun ve tuzlu suda bekletin.");
        karnıyarıkRecipeSteps.offer("3 yemek kaşığı zeytinyağını bir tavada kızdırın. Yemeklik doğradığınız 1 adet soğanı da üzerine ekleyip pembeleşene kadar kavurun. 2 adet doğranmış yeşil biberi ilave edip kavurmaya devam edin.");
        karnıyarıkRecipeSteps.offer("350 gram orta yağlı kıymayı da ekleyin ve kavrulmakta olan soğanlarla birlikte renk alıp, suyunu çekene kadar pişirin.");
        karnıyarıkRecipeSteps.offer("Aralarda karıştırıp pişirme işlemine devam ederek sırasıyla; 2 diş sarımsak, yarım tatlı kaşığı domates salçası, yarım tatlı kaşığı biber salçası, 1'er çay kaşığı tuz ve karabiber ekleyin.");
        karnıyarıkRecipeSteps.offer("2 adet küp doğranmış domatesi ekleyin, 5 dakika kadar pişirdikten sonra ocağın altını kısın ve bir avuç doğranmış maydanozu ekleyip son kez karıştırarak ocaktan alın.");
        karnıyarıkRecipeSteps.offer("Acısını alıp, pijamalı soyduğunuz 6 adet patlıcanı kızartın.");
        karnıyarıkRecipeSteps.offer("Fırına dayanıklı bir kaba yerleştirin.");
        karnıyarıkRecipeSteps.offer("Orta kısımlarından patlıcanları yarın.");
        karnıyarıkRecipeSteps.offer("Hazırladığınız iç harçtan bol bol ekleyin.");
        karnıyarıkRecipeSteps.offer("Patlıcanların üzerine birer cherry domates ve biber dilimleri ekleyin.");
        karnıyarıkRecipeSteps.offer("Karnıyarıkları önceden ısıtılmış 170 derece fırında 20-25 dakika kadar pişirdikten sonra, sıcak olarak, dilerseniz tereyağlı pirinç pilavı eşliğinde servis edin, afiyet olsun!");

        // Aşamaları yazdırma döngüsü
        int stepNumber = 1;
        while (!karnıyarıkRecipeSteps.isEmpty()) {
            System.out.println("Adım " + stepNumber + ": " + karnıyarıkRecipeSteps.poll());
            stepNumber++;
        }

    }
}

public class Main {
    public static void main(String[] args) {

        // Tüm malzemeleri yemeklerin AVL treelerine ekliyoruz
        AVLTree lasagnaTree = new AVLTree();
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Kıyma");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Sıvı Yağ");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Soğan");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Domates");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Salça");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Sarımsak");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Tuz");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Sıcak Su");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Tereyağ");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Süt");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Un");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Karabiber");
        lasagnaTree.root = lasagnaTree.insert(lasagnaTree.root, "Kaşar Peyniri");

        AVLTree lentilSoupTree = new AVLTree();
        lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "kırmızı mercimek");
        lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "soğan");
        lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "havuç");
        lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "salça");
        lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "tuz");
        lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "karabiber");
        lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "kimyon");
        lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "su");
        lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "sıvı yağ");
        lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "toz biber");
        lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "tereyağı");

        AVLTree hunkarBegendiTree = new AVLTree();
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "kuzu but");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "soğan");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "sarımsak");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "zeytin yağı");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "domates");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "domates salçası");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "sıcak su");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "tuz");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "kaşar peyniri");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "tereyağı");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "süt");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "patlıcan");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "un");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "tuz");
        hunkarBegendiTree.root = hunkarBegendiTree.insert(hunkarBegendiTree.root, "karabiber");

        AVLTree karnıyarıkTree = new AVLTree();
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "patlıcan");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "zeytinyağı");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "soğan");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "yeşil biber");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "kıyma");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "sarımsak");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "biber salçası");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "domates salçası");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "tuz");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "karabiber");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "domates");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "sarımsak");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "yeşil sivri biber");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "maydonoz");
        karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "ayçiçek yağı");


        // Tarifi yazdır
        System.out.println("Lazanya Malzemeleri");
        lasagnaTree.printIngredients();
        System.out.println("----------");
        System.out.println("Mercimek Çorbası Malzemeleri");
        lentilSoupTree.printIngredients();
        System.out.println("----------");
        System.out.println("Hünkar Beğendi Malzemeleri");
        hunkarBegendiTree.printIngredients();
        System.out.println("----------");
        System.out.println("Karnıyarık Malzemeleri");
        karnıyarıkTree.printIngredients();


        // Karnıyarık tarifi yazdır
        PrintSteps.karnıyarıkPrintSteps();

    }
}
