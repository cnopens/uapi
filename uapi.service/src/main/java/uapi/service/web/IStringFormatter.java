/**
 * Copyright (C) 2010 The UAPI Authors
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at the LICENSE file.
 *
 * You must gained the permission from the authors if you want to
 * use the project into a commercial product
 */

package uapi.service.web;

import uapi.KernelException;

/**
 * Created by xquan on 5/25/2016.
 */
public interface IStringFormatter<T> {

    String format(T value, Class<T> type) throws KernelException;

    T unformat(String value, Class<T> type) throws KernelException;
}
