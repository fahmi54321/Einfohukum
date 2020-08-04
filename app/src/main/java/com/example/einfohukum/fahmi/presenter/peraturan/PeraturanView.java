package com.example.einfohukum.fahmi.presenter.peraturan;

import com.example.einfohukum.fahmi.model.peraturan.ResultItemPeraturan;
import com.example.einfohukum.fahmi.model.psh.ResultItemPsh;

import java.util.List;

public interface PeraturanView {
    void onSuccess(String message, List<ResultItemPeraturan> result);
    void onError(String message);
    void onEmpty();
    void hideProgress();
    void hideLoading();
}
