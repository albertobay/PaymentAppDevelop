package com.albertobay.paymentapp.presenter;

import com.albertobay.paymentapp.data.model.Issuer;
import com.albertobay.paymentapp.interactor.BankInteractor;

import java.util.List;

/**
 * Created by Alberto Bay on 02,November,2018
 */
public class BankPresenter extends Presenter<BankPresenter.View> {

    private BankInteractor interactor;

    public BankPresenter(BankInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public void onSearchBanks(String apiKey, String pm) {
        getView().showLoading();
        interactor.getAvailableBanks(apiKey, pm).subscribe(banks -> {
            if (!banks.isEmpty() && banks.size() > 0) {
                getView().hideLoading();
                getView().renderBanks(banks);
            } else {
                getView().showPaymentsNotFoundMessage();
            }
        }, Throwable::printStackTrace);
    }

    public void launchBankDetail(Issuer bank, int position) {
        getView().launchSelectBankDetail(bank, position);
    }


    public interface View extends Presenter.View {

        void showLoading();

        void hideLoading();

        void showPaymentsNotFoundMessage();

        void renderBanks(List<Issuer> banks);

        void launchSelectBankDetail(Issuer bakn, int position);

    }
}