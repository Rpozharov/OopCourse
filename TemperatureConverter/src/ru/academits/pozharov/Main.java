package ru.academits.pozharov;

import ru.academits.pozharov.view.DesktopView;
import ru.academits.pozharov.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new DesktopView();
        view.start();
    }

}