package com.evoucher.accv.e_voucher.view.w;

/**
 * Created by lijinfeng on 2017/7/29.
 */

public interface OnPointDragListener {
    void onRemoveStart(AbsDragPointView view);

    void onRemoveEnd(AbsDragPointView view);

    void onRecovery(AbsDragPointView view);
}
