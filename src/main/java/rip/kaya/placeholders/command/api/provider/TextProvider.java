package rip.kaya.placeholders.command.api.provider;

import rip.kaya.placeholders.command.api.argument.CommandArg;
import rip.kaya.placeholders.command.api.exception.CommandExitMessage;
import rip.kaya.placeholders.command.api.parametric.DrinkProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

public class TextProvider extends DrinkProvider<String> {

    public static final TextProvider INSTANCE = new TextProvider();

    @Override
    public boolean doesConsumeArgument() {
        return true;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Nullable
    @Override
    public String provide(@Nonnull CommandArg arg, @Nonnull List<? extends Annotation> annotations) throws CommandExitMessage {
        StringBuilder builder = new StringBuilder(arg.get());
        while (arg.getArgs().hasNext()) {
            builder.append(" ").append(arg.getArgs().next());
        }
        return builder.toString();
    }

    @Override
    public String argumentDescription() {
        return "text";
    }

    @Override
    public List<String> getSuggestions(@Nonnull String prefix) {
        return Collections.emptyList();
    }
}
