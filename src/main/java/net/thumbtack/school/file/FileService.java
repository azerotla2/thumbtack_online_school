package net.thumbtack.school.file;

import com.google.gson.Gson;
import net.thumbtack.school.figures.v3.Ellipse;
import net.thumbtack.school.figures.v3.FilledEllipse;
import net.thumbtack.school.fillStyle.v3.FillStyleException;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;

import java.io.*;

public class FileService {

    public static void  writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(new File(fileName), array);
    }

    public static byte[]  readByteArrayFromBinaryFile(String fileName) throws IOException {
        return readByteArrayFromBinaryFile(new File(fileName));
    }

    public static void  writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(file)){
            fos.write(array);
        }
    }

    public static byte[]  readByteArrayFromBinaryFile(File file) throws IOException {
        try(FileInputStream fis = new FileInputStream(file)){
            byte[] byteFromFile = new byte[(int)file.length()];
            fis.read(byteFromFile);
            return byteFromFile;
        }
    }

    public static byte[]  writeAndReadByteArrayUsingByteStream( byte[] array) throws IOException {
        // REVU тут 2 последовательных действия
        // поэтому должно быть 2 последовательных try, а не вложенные
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
                baos.write(array);
            try(ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray())) {
                byte[] evenIndex = new byte[array.length % 2 + array.length/2];
                for (int index = 0; index < evenIndex.length; index++){
                    evenIndex[index] = (byte) bais.read();
                    bais.skip(1);
                }
                return evenIndex;
            }
        }
    }

    public static void  writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        writeByteArrayToBinaryFileBuffered(new File(fileName), array);
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        return readByteArrayFromBinaryFileBuffered(new File(fileName));
    }

    public static void  writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))){
            bos.write(array);
        }
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
            byte[] byteArray = new byte[(int)file.length()];
            bis.read(byteArray);
            return byteArray;
        }
    }

    public static void  writeEllipseToBinaryFile(File file, Ellipse ellipse) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))){
            dos.writeInt(ellipse.getCenter().getX());
            dos.writeInt(ellipse.getCenter().getY());
            dos.writeInt(ellipse.getXAxis());
            dos.writeInt(ellipse.getYAxis());
        }
    }

    public static Ellipse  readEllipseFromBinaryFile(File file) throws IOException {
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))){
            Ellipse ellipse  = new Ellipse(dis.readInt(), dis.readInt(), dis.readInt(), dis.readInt());
            return ellipse;
        }
    }

    public static void  writeFilledEllipseToBinaryFile(File file, FilledEllipse ellipse) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))){
            dos.writeInt(ellipse.getCenter().getX());
            dos.writeInt(ellipse.getCenter().getY());
            dos.writeInt(ellipse.getXAxis());
            dos.writeInt(ellipse.getYAxis());
            dos.writeUTF(ellipse.getFillStyle().getNameFillStyle());
        }
    }

    public static FilledEllipse  readFilledEllipseFromBinaryFile(File file) throws IOException, FillStyleException {
        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))){
            FilledEllipse fe = new FilledEllipse(dis.readInt(), dis.readInt(), dis.readInt(), dis.readInt(), dis.readUTF());
            return fe;
        }
    }

    public static void  writeEllipseArrayToBinaryFile(File file, Ellipse[] ellipses ) throws IOException {
        // REVU RandomAccessFile тут не нужен, запись последовательная. Хватит и  DataOutputStream
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")){
            for(int index = 0; index < ellipses.length; index++){
                raf.writeInt(ellipses[index].getCenter().getX());
                raf.writeInt(ellipses[index].getCenter().getY());
                raf.writeInt(ellipses[index].getXAxis());
                raf.writeInt(ellipses[index].getYAxis());
            }
        }
    }

    public static Ellipse[]  readEllipseArrayFromBinaryFileReverse(File file) throws IOException {
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw")){
            int countEllipse = (int)file.length()  / 16;
            Ellipse[] ellipses = new Ellipse[countEllipse];
            // REVU читать надо в обратном порядке, а не заносить
            // используйте метод seek
            for(int index = countEllipse-1; index >= 0; index--){
                ellipses[index] =new Ellipse (raf.readInt(), raf.readInt(), raf.readInt(), raf.readInt());
            }
            return ellipses;
        }
    }

    public static void  writeEllipseToTextFileOneLine(File file, Ellipse ellipse) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))){
            bw.write(String.format("%d %d %d %d",
                    ellipse.getCenter().getX(), ellipse.getCenter().getY(), ellipse.getXAxis(), ellipse.getYAxis() ));
        }
    }

    public static Ellipse  readEllipseFromTextFileOneLine(File file) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))){
            String[] split = br.readLine().split(" ");
            return new Ellipse(Integer.valueOf(split[0]), Integer.valueOf(split[1]),
                    Integer.valueOf(split[2]), Integer.valueOf(split[3]));
        }
    }

    public static void  writeEllipseToTextFileFourLines(File file, Ellipse ellipse) throws IOException {
        // REVU кодировка!
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))){
            bw.write(String.format("%d\r\n%d\r\n%d\r\n%d", ellipse.getCenter().getX(), ellipse.getCenter().getY(),
                    ellipse.getXAxis(), ellipse.getYAxis()));
        }
    }

    public static Ellipse  readEllipseFromTextFileFourLines(File file) throws IOException {
        // REVU кодировка!
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
            return new Ellipse (Integer.valueOf(br.readLine()), Integer.valueOf(br.readLine()),
                    Integer.valueOf(br.readLine()), Integer.valueOf(br.readLine()));
        }
    }

    public static void  writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        // REVU кодировка!
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))){
            bw.write(String.format("%s %s %d", trainee.getFirstName(), trainee.getLastName(),
                    trainee.getRating()));
        }
    }

    public static Trainee  readTraineeFromTextFileOneLine(File file) throws IOException, TrainingException, NumberFormatException {
        // REVU кодировка!
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
            String[] split = br.readLine().split(" ");
            return new Trainee (split[0], split[1], Integer.valueOf(split[2]));
        }
    }

    public static void  writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        // REVU кодировка!
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))){
            bw.write(String.format("%s\r\n%s\r\n%d", trainee.getFirstName(), trainee.getLastName(), trainee.getRating()));
        }
    }

    public static Trainee  readTraineeFromTextFileThreeLines(File file) throws IOException, TrainingException {
        // REVU кодировка!
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
            return new Trainee(br.readLine(), br.readLine(), Integer.valueOf(br.readLine()));
        }
    }

    public static void  serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(trainee);
        }
    }

    public static Trainee  deserializeTraineeFromBinaryFile(File file) throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            Trainee trainee = (Trainee)ois.readObject();
            return trainee;
        }
    }

    public static String  serializeTraineeToJsonString(Trainee trainee){
        Gson gson = new Gson();
        return gson.toJson(trainee);
    }

    public static Trainee  deserializeTraineeFromJsonString(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Trainee.class);
    }

    public static void  serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        // REVU кодировка!
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            Gson gson = new Gson();
            gson.toJson(trainee, bw);
        }
    }

    public static Trainee  deserializeTraineeFromJsonFile(File file) throws IOException {
        // REVU кодировка!
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            Gson gson = new Gson();
            return gson.fromJson(br, Trainee.class);
        }
    }
}
