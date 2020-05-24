import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(String.class).annotatedWith(RegNumValue.class).toInstance("reg-num");
        bind(String.class).annotatedWith(ColorValue.class).toInstance("test-color");
    }
}
