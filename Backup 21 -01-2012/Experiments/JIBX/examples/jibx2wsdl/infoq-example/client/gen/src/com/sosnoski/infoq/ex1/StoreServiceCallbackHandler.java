
/**
 * StoreServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

    package com.sosnoski.infoq.ex1;

    /**
     *  StoreServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class StoreServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public StoreServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public StoreServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for retrieveOrder method
            * override this method for handling normal response from retrieveOrder operation
            */
           public void receiveResultretrieveOrder(
                    com.sosnoski.infoq.ex1.Order result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveOrder operation
           */
            public void receiveErrorretrieveOrder(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for placeOrder method
            * override this method for handling normal response from placeOrder operation
            */
           public void receiveResultplaceOrder(
                    java.lang.String result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from placeOrder operation
           */
            public void receiveErrorplaceOrder(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelOrder method
            * override this method for handling normal response from cancelOrder operation
            */
           public void receiveResultcancelOrder(
                    boolean result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelOrder operation
           */
            public void receiveErrorcancelOrder(java.lang.Exception e) {
            }
                


    }
    