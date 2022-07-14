package reflection.exam;
import java.util.*;
public class ClassAnalyzer {
    private static final List<String> JDK_PACKAGE_PREFIXES =
            Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");

    public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> inputClass) {
        PopupTypeInfo popupTypeInfo = new PopupTypeInfo();

        /** Complete the Code **/


        popupTypeInfo.setPrimitive(inputClass.isPrimitive())
                     .setInterface(inputClass.isInterface())
                     .setEnum(inputClass.isEnum())
                     .setName(inputClass.getSimpleName())
                     .setJdk(isJdkClass(inputClass))
                     .addAllInheritedClassNames(getAllInheritedClassNames(inputClass));

        return popupTypeInfo;
    }

    /*********** Helper Methods ***************/

    public static boolean isJdkClass(Class<?> inputClass) {
        /** Complete the code
         Hint: What does inputClass.getPackage() return when the class is a primitive type?
         **/
        String packageName = inputClass.getPackageName();
        if (JDK_PACKAGE_PREFIXES.contains(packageName)) {
            return true;
        }
        return false;
    }

    public static String[] getAllInheritedClassNames(Class<?> inputClass) {
        /** Complete the code
         Hints: What does inputClass.getSuperclass() return when the inputClass doesn't inherit from any class?
         What does inputClass.getSuperclass() return when the inputClass is a primitve type?
         **/
        Class<?> superclass = inputClass.getSuperclass();
        String[] strings = new String[10];
        if (superclass != null) {
            strings[0] = "Primitive";
        }
        strings[0] = superclass.getSimpleName();
        return strings;
    }
}