package reflection.field;

import java.lang.reflect.Field;

public class Main {


    /**
     *
     * Class.getDeclaredFields() - 현재 클래스에 선언된 필드를 반환, 접근제어자에 상관 없이 모든 필드를 포함, 대신 상속된 필드는 제외
     * Class.getFields() - 상위클래스로 부터 상속된 필드를 포함해서 public 필드를 반환
     *
     * 필드이름을 안다면 .getDeclaredField(fieldName) or .getField(fieldName) 조회가능, 필드가 존재하지 않을 시
     * NoSuchFieldException 발생
     *
     *
     * Synthetic Fields
     * 클래스에 명시하여 선언한 필드 외에도 Java Compiler 가 내부적으로 동작을 위해 인위적으로 생성한 필드
     * 실행 할 때 Reflection 을 사용해 찾지 않는 한 보이지 않는 필드
     * 이러한 필드는 컴파일러에 특정한 이름을 갖고 주로 변경하지 않음
     * Field.isSynthetic() 을 통해 Synthetic 여부 확인 가능
     *
     * Reflection 으로 클래스나 필드 객체의 정보를 실행 시 동적으로 가져 올 수 있음. 이 기능으로 객체타입을 미리 알 수 없거나
     * 프로그램이 실행되기 전까지 존재하지 않 제네릭 알고리즘과 라이브러리를 작성 할 수 있음.
     *
     */
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Movie movie = new Movie("avengers",
                2010,
                12.99,
                true,
                Category.ACTION);

        printDeclaredFieldsInto(Movie.class,movie);
        Field minimum_price = Movie.class.getDeclaredField("MINIMUM_PRICE");
        double o = (double) minimum_price.get(null);
        System.out.println("MINIMUM_PRICE : " + o);
    }

    public static<T> void printDeclaredFieldsInto(Class<T> clazz,T instance) throws IllegalAccessException {
        for (Field field :
                clazz.getDeclaredFields()) {
            System.out.println(String.format("calss : %s Field name : %s type : %s", field.getDeclaringClass().getSimpleName(), field.getName(), field.getType()
                                                                                                .getSimpleName()));
            System.out.println(String.format("Is synthetic Field : %s", field.isSynthetic()));
            System.out.println(String.format("Field value is : %s",field.get(instance)));
            System.out.println();
        }
    }
    public static class Movie extends Product{
        //정적 필드는 Reflection getFiled(Object) 사용시 null 이 인자로 들어와도(구체적인 객체인스턴스가 없어도) 정상적으로 값을 얻을 수 있다.
        private static final double MINIMUM_PRICE = 10.99;
        private boolean isRelesed;
        private Category category;
        private double actualPrice;

        public Movie(String name, int year,double price, boolean isReleased, Category category) {
            super(name, year);
            this.isRelesed = isReleased;
            this.category = category;
            this.actualPrice=Math.max(price, MINIMUM_PRICE);
        }
        public class MovieStats{
            private double timeWatched;

            public MovieStats(double timeWatched) {
                this.timeWatched = timeWatched;
            }
            public double getRevenue(){
                return timeWatched * actualPrice;
            }

        }
    }
    //SUPER CLASS
    public static class Product{
        protected String name;
        protected int year;
        protected double actualPrice;

        public Product(String name, int year) {
            this.name = name;
            this.year = year;
        }
    }
    public enum Category{
        ADVENTURE,
        ACTION,
        COMEDY
    }
}
