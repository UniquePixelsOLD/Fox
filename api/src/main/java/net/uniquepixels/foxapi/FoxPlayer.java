package net.uniquepixels.foxapi;

import java.util.Locale;
import java.util.UUID;

public record FoxPlayer(UUID uid, Locale selectedLocale) {
}
