package com.albertobay.paymentapp.presenter;

import com.albertobay.paymentapp.data.model.Installment;
import com.albertobay.paymentapp.data.model.PayerCost;
import com.albertobay.paymentapp.interactor.InstallmentInteractor;

import java.util.List;

/**
 * Created by Alberto Bay on 02,November,2018
 */
public class InstallmentPresenter extends Presenter<InstallmentPresenter.View> {

    private InstallmentInteractor interactor;

    public InstallmentPresenter(InstallmentInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public void onSearchInstallments(String publicApiKey, String amount, String pm, String bank) {
        getView().showLoading();
        interactor.getInstallments(publicApiKey, amount, pm, bank).subscribe(installments -> {
            if (!installments.isEmpty() && installments.size() > 0) {
                getView().hideLoading();
                getView().renderInstallments(installments);
            } else {
                getView().showPaymentsNotFoundMessage();
            }
        }, Throwable::printStackTrace);
    }

    public void launchInstallment(PayerCost bank, int position) {
        getView().launchSelectedInstallmentDetail(bank, position);
    }


    public interface View extends Presenter.View {

        void showLoading();

        void hideLoading();

        void showPaymentsNotFoundMessage();

        void renderInstallments(List<Installment> installments);

        void launchSelectedInstallmentDetail (PayerCost payerCost, int position);

    }
}