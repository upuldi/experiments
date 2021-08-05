

/**
 * StoreService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

    package com.sosnoski.infoq.ex1;

    /*
     *  StoreService java interface
     */

    public interface StoreService {
          

        /**
          * Auto generated method signature
          * Retrieve order information.
                    * @param retrieveOrder
                
         */

         
                     public com.sosnoski.infoq.ex1.Order retrieveOrder(

                        java.lang.String id)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * Retrieve order information.
                * @param retrieveOrder
            
          */
        public void startretrieveOrder(

            java.lang.String id,

            final com.sosnoski.infoq.ex1.StoreServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * Submit a new order.
                    * @param placeOrder
                
         */

         
                     public java.lang.String placeOrder(

                        com.sosnoski.infoq.ex1.Order order)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * Submit a new order.
                * @param placeOrder
            
          */
        public void startplaceOrder(

            com.sosnoski.infoq.ex1.Order order,

            final com.sosnoski.infoq.ex1.StoreServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * Cancel order. This can only be used for orders which have not been shipped.
                    * @param cancelOrder
                
         */

         
                     public boolean cancelOrder(

                        java.lang.String id)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * Cancel order. This can only be used for orders which have not been shipped.
                * @param cancelOrder
            
          */
        public void startcancelOrder(

            java.lang.String id,

            final com.sosnoski.infoq.ex1.StoreServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    