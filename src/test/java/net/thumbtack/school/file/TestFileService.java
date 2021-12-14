package net.thumbtack.school.file;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import net.thumbtack.school.figures.v3.FilledEllipse;

import net.thumbtack.school.figures.v3.Ellipse;
import net.thumbtack.school.fillStyle.v3.FillStyle;
import net.thumbtack.school.fillStyle.v3.FillStyleException;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TestFileService {
    @TempDir
    public Path tempDir;

    @Test
    public void testFileReadWriteByteArray1() throws IOException {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        File file = Files.createFile(tempDir.resolve("test.dat")).toFile();
        FileService.writeByteArrayToBinaryFile(file, arrayToWrite);
        assertTrue(file.exists());
        assertEquals(arrayToWrite.length, file.length());
        byte[] arrayRead = FileService.readByteArrayFromBinaryFile(file);
        assertArrayEquals(arrayToWrite, arrayRead);
    }

    @Test
    public void testFileReadWriteByteArray2() throws IOException {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        String fileName = Files.createFile(tempDir.resolve("test.dat")).toFile().getAbsolutePath();
        FileService.writeByteArrayToBinaryFile(fileName, arrayToWrite);
        File file = new File(fileName);
        assertTrue(file.exists());
        assertEquals(arrayToWrite.length, file.length());
        byte[] arrayRead = FileService.readByteArrayFromBinaryFile(fileName);
        assertArrayEquals(arrayToWrite, arrayRead);
    }

    @Test
    public void testByteStreamReadWriteByteArray() throws IOException {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        byte[] result = FileService.writeAndReadByteArrayUsingByteStream(arrayToWrite);
        assertArrayEquals(new byte[]{0, 5, 67}, result);
    }

    @Test
    public void testFileReadWriteByteArray1Buffered() throws IOException {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        File file = Files.createFile(tempDir.resolve("test.dat")).toFile();
        FileService.writeByteArrayToBinaryFileBuffered(file, arrayToWrite);
        assertTrue(file.exists());
        assertEquals(arrayToWrite.length, file.length());
        byte[] arrayRead = FileService.readByteArrayFromBinaryFileBuffered(file);
        assertArrayEquals(arrayToWrite, arrayRead);
    }

    @Test
    public void testFileReadWriteByteArray2Buffered() throws IOException {
        byte[] arrayToWrite = {0, 1, 5, -34, 67, -123};
        String fileName = Files.createFile(tempDir.resolve("test.dat")).toFile().getAbsolutePath();
        FileService.writeByteArrayToBinaryFileBuffered(fileName, arrayToWrite);
        File file = new File(fileName);
        assertTrue(file.exists());
        assertEquals(arrayToWrite.length, file.length());
        byte[] arrayRead = FileService.readByteArrayFromBinaryFileBuffered(fileName);
        assertArrayEquals(arrayToWrite, arrayRead);
    }

    @Test
    public void testFileReadWriteEllipseBinary() throws IOException {
        Ellipse ellipseToWrite = new Ellipse(10000, 10000, 20000, 20000);
        File file = Files.createFile(tempDir.resolve("test.dat")).toFile();
        FileService.writeEllipseToBinaryFile(file, ellipseToWrite);
        assertTrue(file.exists());
        assertEquals(16, file.length());
        Ellipse ellipseRead = FileService.readEllipseFromBinaryFile(file);
        assertEquals(ellipseToWrite, ellipseRead);
    }

    @Test
    public void testFileReadWriteFilledEllipseBinary() throws FillStyleException, IOException {
        FilledEllipse ellipseToWrite = new FilledEllipse(10000, 10000, 20000, 20000, FillStyle.SOLID_RED);
        File file = Files.createFile(tempDir.resolve("test.dat")).toFile();
        FileService.writeFilledEllipseToBinaryFile(file, ellipseToWrite);
        assertTrue(file.exists());
        assertEquals(27, file.length());
        FilledEllipse ellipseRead = FileService.readFilledEllipseFromBinaryFile(file);
        assertEquals(ellipseToWrite, ellipseRead);
    }

    @Test
    public void testFileReadEllipseArrayBinary() throws IOException {
        int count = 5;
        Ellipse[] ellipsesToWrite = new Ellipse[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            ellipsesToWrite[i] = new Ellipse(random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt());
        }
        File file = Files.createFile(tempDir.resolve("test.dat")).toFile();
        FileService.writeEllipseArrayToBinaryFile(file, ellipsesToWrite);
        assertTrue(file.exists());
        assertEquals(count * 16, file.length());
        Ellipse[] ellipsesRead = FileService.readEllipseArrayFromBinaryFileReverse(file);
        for (int i = 0; i < ellipsesRead.length / 2; i++) {
            Ellipse temp = ellipsesRead[i];
            ellipsesRead[i] = ellipsesRead[ellipsesRead.length - i - 1];
            ellipsesRead[ellipsesRead.length - i - 1] = temp;
        }
        assertArrayEquals(ellipsesToWrite, ellipsesRead);
    }

    @Test
    public void testFileReadWriteEllipseTextOneLine() throws IOException {
        Ellipse ellipseToWrite = new Ellipse(10000, 10000, 20000, 20000);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.writeEllipseToTextFileOneLine(file, ellipseToWrite);
        assertTrue(file.exists());
        assertEquals(1, Files.readAllLines(file.toPath()).size());
        Ellipse ellipseRead = FileService.readEllipseFromTextFileOneLine(file);
        assertEquals(ellipseToWrite, ellipseRead);
    }

    @Test
    public void testFileReadWriteEllipseTextFourLines() throws IOException {
        Ellipse ellipseToWrite = new Ellipse(10000, 10000, 20000, 20000);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.writeEllipseToTextFileFourLines(file, ellipseToWrite);
        assertTrue(file.exists());
        assertEquals(4, Files.readAllLines(file.toPath()).size());
        Ellipse ellipseRead = FileService.readEllipseFromTextFileFourLines(file);
        assertEquals(ellipseToWrite, ellipseRead);
    }

    @Test
    public void testFileReadWriteTraineeTextOneLine() throws NumberFormatException, TrainingException, IOException {
        Trainee traineeToWrite = new Trainee("Иван", "Иванов", 2);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.writeTraineeToTextFileOneLine(file, traineeToWrite);
        assertTrue(file.exists());
        assertEquals(1, Files.readAllLines(file.toPath()).size());
        Trainee traineeRead = FileService.readTraineeFromTextFileOneLine(file);
        assertEquals(traineeToWrite, traineeRead);
    }

    @Test
    public void testFileReadWriteTraineeTextThreeLines() throws NumberFormatException, TrainingException, IOException {
        Trainee traineeToWrite = new Trainee("Иван", "Иванов", 2);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.writeTraineeToTextFileThreeLines(file, traineeToWrite);
        assertTrue(file.exists());
        assertEquals(3, Files.readAllLines(file.toPath()).size());
        Trainee traineeRead = FileService.readTraineeFromTextFileThreeLines(file);
        assertEquals(traineeToWrite, traineeRead);
    }

    @Test
    public void testFileSerializeDeserializeTraineeBinary() throws TrainingException, ClassNotFoundException, IOException {
        Trainee traineeToWrite = new Trainee("Иван", "Иванов", 2);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.serializeTraineeToBinaryFile(file, traineeToWrite);
        assertTrue(file.exists());
        Trainee traineeRead = FileService.deserializeTraineeFromBinaryFile(file);
        assertEquals(traineeToWrite, traineeRead);
    }

    @Test
    public void testStringSerializeDeserializeTraineeJson() throws TrainingException {
        Trainee traineeToWrite = new Trainee("Иван", "Иванов", 2);
        String json = FileService.serializeTraineeToJsonString(traineeToWrite);
        Trainee traineeRead = FileService.deserializeTraineeFromJsonString(json);
        assertEquals(traineeToWrite, traineeRead);
    }

    @Test
    public void testFileSerializeDeserializeTraineeJson() throws TrainingException, IOException {
        Trainee traineeToWrite = new Trainee("Иван", "Иванов", 2);
        File file = Files.createFile(tempDir.resolve("test.txt")).toFile();
        FileService.serializeTraineeToJsonFile(file, traineeToWrite);
        assertTrue(file.exists());
        Trainee traineeRead = FileService.deserializeTraineeFromJsonFile(file);
        assertEquals(traineeToWrite, traineeRead);
    }

    @Test
    public void testThrowsIOException() {
        Method[] declaredMethods = FileService.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals("serializeTraineeToJsonString") || method.getName().equals("deserializeTraineeFromJsonString")) {
                continue;
            }
            if (!Modifier.isPublic(method.getModifiers())) {
                continue;
            }
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            boolean throwIOException = false;
            for (Class<?> exception : exceptionTypes) {
                if (exception == IOException.class) {
                    throwIOException = true;
                    break;
                }
            }
            if (!throwIOException) {
                fail("Every public method must throw IOException");
            }
        }
    }

}
