/**
 * Copyright 2016 Yahoo Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yahoo.pulsar.client.impl.auth;

import java.security.KeyManagementException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import com.yahoo.pulsar.client.api.AuthenticationDataProvider;
import com.yahoo.pulsar.common.util.SecurityUtility;

public class AuthenticationDataTls implements AuthenticationDataProvider {

    protected final X509Certificate[] certificates;
    protected final PrivateKey privateKey;

    public AuthenticationDataTls(String certFilePath, String keyFilePath) throws KeyManagementException {
        if (certFilePath == null) {
            throw new IllegalArgumentException("certFilePath must not be null");
        }
        if (keyFilePath == null) {
            throw new IllegalArgumentException("keyFilePath must not be null");
        }
        certificates = SecurityUtility.loadCertificatesFromPemFile(certFilePath);
        privateKey = SecurityUtility.loadPrivateKeyFromPemFile(keyFilePath);
    }

    /*
     * TLS
     */

    @Override
    public boolean hasDataForTls() {
        return true;
    }

    @Override
    public X509Certificate[] getTlsCertificates() {
        return certificates;
    }

    @Override
    public PrivateKey getTlsPrivateKey() {
        return privateKey;
    }

}
