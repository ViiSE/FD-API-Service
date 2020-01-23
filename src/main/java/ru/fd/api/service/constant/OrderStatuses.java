/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.constant;

public class OrderStatuses {
    public static final short NOT_PROCESSED = 0;
    public static final short READY_TO_ASSEMBLY = 1;
    public static final short LACK_OF_PRODUCTS_QUANTITY = 2;
    public static final short ASSEMBLED = 3;
    public static final short CANCELLED = 4;
    public static final short COMPLETED = 5;
    public static final short INTERNAL_SERVER_ERROR = 500;
}
