/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fill_tool;

import _55._49._164._110.dolparcel2ega.ArrayOfParcel;

/**
 *
 * @author SSI
 */
public class C_controller {

    
    
    public static ArrayOfParcel getParcel(java.lang.String provincecode, java.lang.String amphoecode, java.lang.String ns4No) {
        _55._49._164._110.dolparcel2ega.DolParcelAll service = new _55._49._164._110.dolparcel2ega.DolParcelAll();
        _55._49._164._110.dolparcel2ega.DolParcelAllPortType port = service.getDolParcelAllHttpSoap11Endpoint();
        return port.getParcel(provincecode, amphoecode, ns4No);
    }
    
}
