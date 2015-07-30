package util;

import data.TextType;

public class TypeIdentifier {

	private static final String PATTERN_NUMBER = "[0-9,\\.$]*";

	public static TextType identifyType(String text) {
		if (text.matches(PATTERN_NUMBER))
			return TextType.NUMBER;
		else
			return TextType.ALPHANUMERIC;
	}

}
