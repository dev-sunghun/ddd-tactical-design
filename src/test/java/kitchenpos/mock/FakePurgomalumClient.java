package kitchenpos.mock;

import kitchenpos.shared.client.PurgomalumClient;

public class FakePurgomalumClient implements PurgomalumClient {

    private static final String BAD_WORD = "bad";

    public boolean containsProfanity(final String text) {
        return text.contains(BAD_WORD);
    }
}
