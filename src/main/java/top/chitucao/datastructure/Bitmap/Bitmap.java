package top.chitucao.datastructure.Bitmap;

/**
 * Bitmap boolean
 * 这里的index代表状态，例如一年365天第index天的打卡状态
 * bitMap的n长度表示一共有n条数据，这里表示365天的数据
 * <p>
 * 以bit为存储单位的数据结构
 * 对于给定第i位，1表示true，0表示false
 * 对于只需进行布尔存取的情况来说，是非常节省空间且高效的方案
 * <p>
 * 参考地址  https://article.itxueyuan.com/nl0D1O
 */
public class Bitmap {

    private byte[] bytes;

    private int length;


    public Bitmap(int length) {
        this.length = length;
        bytes = new byte[length % 8 == 0 ? length / 8 : length / 8 + 1];
    }

    public void set(int index, boolean value) {
        if (value) {
            bytes[index >> 3] |= 1 << (index & 7);
        } else {
            bytes[index >> 3] &= ~(1 << (index & 7));
        }
    }

    public boolean get(int index) {
        int mod = index & 7;
        return (bytes[index >> 3] & (0b11111111 >>> (7 - mod))) >> mod != 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Bitmap[");
        for (int i = 0; i < length; i++) {
            builder.append(get(i)).append(',');
        }
        if (bytes.length != 0) {
            builder = builder.deleteCharAt(builder.length() - 1);
        }
        builder.append(']');
        return builder.toString();
    }

}
