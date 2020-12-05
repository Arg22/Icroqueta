package com.example.icroqueta.database.tablas;

import android.database.sqlite.SQLiteDatabase;
import com.example.icroqueta.database.entidades.Producto;

public class ProductoTable {
    public static final String TABLE_NAME = "Producto";
    public static final String ID_PRODUCTO = "id_producto";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String PRECIO_UD = "precio_ud";
    public static final String STOCK = "stock";
    public static final String DESCUENTO = "descuento";
    public static final String IMAGEN = "imagen";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_PRODUCTO + " INTEGER PRIMARY KEY," +
                    NOMBRE + " TEXT," +
                    DESCRIPCION + " TEXT, " +
                    PRECIO_UD + " REAL," +
                    STOCK + " INTEGER," +
                    DESCUENTO + " REAL," +
                    IMAGEN + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        insertIniciales(db);
    }

    public static void onDrop(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }


    /**Aqui se insertarian por primera vez los valores de la tabla
     *
     * @param db nuestra base de datos
     */
    public static void insertIniciales(SQLiteDatabase db) {
        Producto p1 = new Producto("Jamon y queso", "Croquetas con delicioso jamón dulce y queso curado.", 1.05, 100, 0, "https://saltandoladieta.com/wp-content/uploads/2019/02/croquetas-caseras-jamon-receta-deliciosa.jpg");
        Producto p2 = new Producto("Jamon y queso sin gluten", "Croquetas con delicioso jamón dulce y queso curado, pero con menos gluten... Más bien nada de él.", 1.05, 100, 0, "https://www.hogarmania.com/archivos/201801/croquetas-jamon-detalle-1280x720x80xX.jpg");
        Producto p3 = new Producto("Pollo asado", "Deliciosas croquetas de pollo con ajo.", 1.10, 10, 0, "https://www.divinacocina.es/wp-content/uploads/croquetas-de-salmon.jpg");
        Producto p4 = new Producto("Tofu sabor pollo", "Son croquetas de pollo, pero le decimos a la gente que es de tofu para que las compren, vaya pringados.", 1.10, 100, 0, "https://vod-hogarmania.atresmedia.com/cocinatis/images/images01/2020/01/16/5e206222fa7dec0001ed750d/1239x697.jpg");
        Producto p5  = new Producto("Bacalao", "Bacalao recién comprado de la lonja de turquia.", 1.85, 100, 0, "https://t2.rg.ltmcdn.com/es/images/8/0/3/croquetas_de_bacalao_caseras_59308_600_square.jpg");
        Producto p6  = new Producto("Salmón ahumado", "Rico salmón cocinado a la piedra", 1.75, 10, 0, "https://www.divinacocina.es/wp-content/uploads/croquetas-de-salmon.jpg");
        Producto p7  = new Producto("Salmón ahumado sin gluten", "Rico salmón cocinado a la piedra, pero con menos gluten... Más bien nada de él.", 1.75, 10, 0, "https://www.divinacocina.es/wp-content/uploads/croquetas-de-bacalao.jpg");
        Producto p8  = new Producto("Quinoa roja", "Croquetas rebozadas con quinoa frita roja.", 1.25, 50, 0, "https://img-global.cpcdn.com/recipes/b63f10174b93abd6/1200x630cq70/photo.jpg");
        Producto p9  = new Producto("Queso de cabra", "Es de cabra porque no nos quedaba de burra", 1.90, 10, 0, "https://www.recetin.com/wp-content/uploads/2013/07/croquetas_queso.jpg.webp");
        Producto p10  = new Producto("Queso manchego sin lactosa", "Son croquetas rellenas de queso sin lactosa, o eso pone en la caja del producto.", 2.00, 100, 0, "https://www.recetin.com/wp-content/uploads/2010/10/croquetas.jpg");
        Producto p11  = new Producto("Acelgas", "Que asco, no se como la gente compra este producto", 2.45, 50, 0, "https://static.mujerhoy.com/noticias/201710/06/media/cortadas/croquetas-k3mC-U40960252540aWE-644x483@MujerHoy.jpg");
        Producto p12  = new Producto("Vainilla y dulce de leche", "Croquetas con dulces con leche condensada con sabor a vainilla.", 2.45, 50, 0, "https://www.hogarmania.com/archivos/201311/croquetas-de-ave-y-vainilla-xl-668x400x80xX.jpg");
        Producto p13  = new Producto("Chocolate y dulce de leche", "Croquetas dulces con leche condensada con sabor a chocolate.", 2.45, 50, 0, "https://canalcocina.es/medias/_cache/zoom-f46025d0bc49437cc3227d0ef9381d15-920-518.jpg");
        Producto p14  = new Producto("Fresas naturales", "La vecina Paca y su marido Eleuterio me ha dado una caja de fresas y no se que hacer con ellas.", 2.45, 50, 0, "https://i.pinimg.com/originals/36/cf/47/36cf476a57516dbeb29e94b26e18cc2c.jpg");
        Producto p15  = new Producto("Oreo", "Croquetas con sabor a ordeo, pero de la marca blanca el mercadona.", 4.00, 50, 0, "https://3.bp.blogspot.com/-HUTNEbCdpWc/VDLlbz4ymxI/AAAAAAAAPSU/l63CrYnobZs/s1600/DSC_0078.JPG");


        db.insert(TABLE_NAME, null, p1.mapearAContenValues());
        db.insert(TABLE_NAME, null, p2.mapearAContenValues());
        db.insert(TABLE_NAME, null, p3.mapearAContenValues());
        db.insert(TABLE_NAME, null, p4.mapearAContenValues());
        db.insert(TABLE_NAME, null, p5.mapearAContenValues());
        db.insert(TABLE_NAME, null, p6.mapearAContenValues());
        db.insert(TABLE_NAME, null, p7.mapearAContenValues());
        db.insert(TABLE_NAME, null, p8.mapearAContenValues());
        db.insert(TABLE_NAME, null, p9.mapearAContenValues());
        db.insert(TABLE_NAME, null, p10.mapearAContenValues());
        db.insert(TABLE_NAME, null, p11.mapearAContenValues());
        db.insert(TABLE_NAME, null, p12.mapearAContenValues());
        db.insert(TABLE_NAME, null, p13.mapearAContenValues());
        db.insert(TABLE_NAME, null, p14.mapearAContenValues());
        db.insert(TABLE_NAME, null, p15.mapearAContenValues());
    }
}
