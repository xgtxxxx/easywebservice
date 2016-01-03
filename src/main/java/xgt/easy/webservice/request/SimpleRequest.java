package xgt.easy.webservice.request;

import xgt.easy.webservice.Request;
import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.annotation.SupperAvailable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by xgt on 2016/1/3.
 */
@SupperAvailable
public class SimpleRequest extends Request {
    @Path(order = 2)
    private String subPrivate = "subPrivate";
    protected String subProtected = "subProtected";
    @Path(order = 1)
    public String subPublic = "subPublic";

    public static void main(String[] args) throws IllegalAccessException {
        SimpleRequest request = new SimpleRequest();
        List<Field> fields = request.getFields();
        for(Field field:fields){
            System.out.println(field.getName());
        }
        request.sortFields(fields);
    }

    public void sortFields(List<Field> fields){
        List<Field> paths = new ArrayList<Field>();
        List<Field> others = new ArrayList<Field>();
        for (Field field : fields){
            Path path = field.getAnnotation(Path.class);
            if(path!=null){
                paths.add(field);
            }else{
                others.add(field);
            }
        }

        Field[] fs = new Field[paths.size()];
        paths.toArray(fs);
        Field temp = null;
        int len = fs.length;
        for(int i=0 ;i<len-1;i++){
            Field min = fs[i];
            int index = i;
            for(int j = i+1; j<len; j++){
                if(min.getAnnotation(Path.class).order()>fs[j].getAnnotation(Path.class).order()){
                    min = fs[j];
                    index = j;
                }
            }
            temp = fs[i];
            fs[i] = fs[index];
            fs[index] = temp;
        }

        paths.clear();
        paths.addAll(Arrays.asList(fs));
        for(Field field:paths){
            System.out.println(field.getName());
        }
    }

    public static void selectSort(int a[]) {
        int temp = 0;
        int len = a.length;
        for (int i = 0; i < len - 1; i++) {
            int min = a[i];
            int index = i;
            for (int j = i + 1; j < len; j++) {
                if (min > a[j]) {
                    min = a[j];
                    index = j;
                }
            }
            temp = a[i];
            a[i] = a[index];
            a[index] = temp;
        }
    }

    public List<Field> getFields(){
        List<Field> fields = new ArrayList<Field>();
        Class clazz = this.getClass();
        Annotation available = clazz.getAnnotation(SupperAvailable.class);
        while (clazz!=null){
            fields.addAll(0,Arrays.asList(clazz.getDeclaredFields()));
            if(available!=null){
                clazz = clazz.getSuperclass();
            }else{
                clazz = null;
            }
        }
        return fields;
    }
}
