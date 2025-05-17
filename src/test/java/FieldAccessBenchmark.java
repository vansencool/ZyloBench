import net.vansen.zylobench.ZyloBench;
import net.vansen.zylobench.calculate.CalculateType;
import net.vansen.zylobench.tasks.Normal;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class FieldAccessBenchmark {

    static final TestTarget target = new TestTarget();

    static final VarHandle varHandle;
    static final Field reflectionField;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(TestTarget.class, MethodHandles.lookup());
            varHandle = lookup.findVarHandle(TestTarget.class, "privateValue", short.class);

            reflectionField = TestTarget.class.getDeclaredField("privateValue");
            reflectionField.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ZyloBench.make()
                .warm(5000)
                .runs(10000)
                .accuracy(3)
                .calculateBasedOn(CalculateType.TOTAL)

                // Getters
                .add(Normal.as("Direct Get", () -> {
                    short x = target.value;
                }))
                .add(Normal.as("Getter Method", () -> {
                    short x = target.getPrivateValue();
                }))
                .add(Normal.as("Reflection Get", () -> {
                    try {
                        short x = (short) reflectionField.get(target);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .add(Normal.as("VarHandle Get", () -> {
                    short x = (short) varHandle.get(target);
                }))

                // Setters
                .add(Normal.as("Direct Set", () -> target.value = 42))
                .add(Normal.as("Setter Method", () -> target.setPrivateValue((short) 42)))
                .add(Normal.as("Reflection Set", () -> {
                    try {
                        reflectionField.set(target, (short) 42);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .add(Normal.as("VarHandle Set", () -> varHandle.set(target, (short) 42)))

                .go();
    }

    public static class TestTarget {
        public short value = 42;
        private short privateValue = 42;

        public short getPrivateValue() {
            return privateValue;
        }

        public void setPrivateValue(short value) {
            this.privateValue = value;
        }
    }
}