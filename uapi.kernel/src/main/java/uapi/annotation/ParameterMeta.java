package uapi.annotation;

import uapi.InvalidArgumentException;
import uapi.KernelException;
import uapi.helper.ArgumentChecker;
import uapi.helper.StringHelper;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.Objects;

/**
 * A meta object for a formal parameter
 */
public class ParameterMeta {

    private final Builder _builder;

    public ParameterMeta(
            final Builder builder
    ) throws InvalidArgumentException {
        ArgumentChecker.notNull(builder, "builder");
        this._builder = builder;
    }

    public String getName() {
        return this._builder._name;
    }

    public String getType() {
        return this._builder._type;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(
            final Element parameterElement,
            final BuilderContext builderContext
    ) throws KernelException {
        ArgumentChecker.notNull(parameterElement, "parameterElement");
        ArgumentChecker.notNull(builderContext, "builderContext");
        if (parameterElement.getKind() != ElementKind.PARAMETER) {
            throw new KernelException(
                    "The element is not a parameter element - {}",
                    parameterElement.getSimpleName().toString());
        }

        return new Builder()
                .setName(parameterElement.getSimpleName().toString())
                .setType(parameterElement.asType().toString());
    }

    public static final class Builder extends uapi.helper.Builder<ParameterMeta> {

        private String _name;
        private String _type;

        private Builder() { }

        public Builder setName(
                final String name
        ) throws KernelException {
            checkStatus();
            this._name = name;
            return this;
        }

        public String getName() {
            return this._name;
        }

        public Builder setType(
                final String type
        ) throws KernelException {
            checkStatus();
            this._type = type;
            return this;
        }

        @Override
        protected ParameterMeta buildInstance() {
            ArgumentChecker.notEmpty(this._name, "name");
            ArgumentChecker.notEmpty(this._type, "type");
            return new ParameterMeta(this);
        }

        @Override
        public String toString() {
            return StringHelper.makeString(
                    "ParameterMeta[" +
                            "name={}, " +
                            "type={}, ",
                    this._name, this._type
            );
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Builder builder = (Builder) o;
            return Objects.equals(_name, builder._name) &&
                    Objects.equals(_type, builder._type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_name, _type);
        }
    }
}
