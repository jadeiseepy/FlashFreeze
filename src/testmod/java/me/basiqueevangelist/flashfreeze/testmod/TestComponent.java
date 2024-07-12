package me.basiqueevangelist.flashfreeze.testmod;

import org.ladysnake.cca.api.v3.component.Component;

public interface TestComponent extends Component {
    int getValue();
    void setValue(int value);
}
