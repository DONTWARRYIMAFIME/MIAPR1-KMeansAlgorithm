package by.miapr.kmeans.alghoritm;

import java.io.*;

public abstract class ObjectCloner<T>
{
    private ObjectCloner(){}

    public static Object deepCopy(Object oldObj)
    {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try
        {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);

            oos.writeObject(oldObj);
            oos.flush();
            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bin);

            return ois.readObject();
        }
        catch(Exception e)
        {
            System.out.println("Exception in ObjectCloner = " + e);
            try {
                throw(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            return null;
        }
        finally
        {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
