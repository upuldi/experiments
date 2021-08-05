
/**
 * StoreServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */
        package com.sosnoski.infoq.ex1;

        

        /*
        *  StoreServiceStub java implementation
        */

        
        public class StoreServiceStub extends org.apache.axis2.client.Stub
        implements StoreService{
        protected org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();

        private static int counter = 0;

        private static synchronized java.lang.String getUniqueSuffix(){
            // reset the counter if it is greater than 99999
            if (counter > 99999){
                counter = 0;
            }
            counter = counter + 1; 
            return java.lang.Long.toString(System.currentTimeMillis()) + "_" + counter;
        }

    
    private void populateAxisService() throws org.apache.axis2.AxisFault {

     //creating the Service with a unique name
     _service = new org.apache.axis2.description.AxisService("StoreService" + getUniqueSuffix());
     addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[3];
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://ws.sosnoski.com/order/wsdl/StoreService", "retrieveOrder"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[0]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://ws.sosnoski.com/order/wsdl/StoreService", "placeOrder"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[1]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("http://ws.sosnoski.com/order/wsdl/StoreService", "cancelOrder"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[2]=__operation;
            
        
        }

    //populates the faults
    private void populateFaults(){
         


    }

    /**
      *Constructor that takes in a configContext
      */

    public StoreServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
       java.lang.String targetEndpoint)
       throws org.apache.axis2.AxisFault {
         this(configurationContext,targetEndpoint,false);
   }


   /**
     * Constructor that takes in a configContext  and useseperate listner
     */
   public StoreServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
         //To populate AxisService
         populateAxisService();
         populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);
        
	
        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
        
    
    }

    /**
     * Default Constructor
     */
    public StoreServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        
                    this(configurationContext,"http://localhost:8080/axis2/services/StoreService" );
                
    }

    /**
     * Default Constructor
     */
    public StoreServiceStub() throws org.apache.axis2.AxisFault {
        
                    this("http://localhost:8080/axis2/services/StoreService" );
                
    }

    /**
     * Constructor taking the target endpoint
     */
    public StoreServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }



        


       /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
       private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
       return returnMap;
    }

    
    
    private javax.xml.namespace.QName[] opNameArray = null;
    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        

        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;   
            }
        }
        return false;
    }
     //http://localhost:8080/axis2/services/StoreService
    private static final org.jibx.runtime.IBindingFactory bindingFactory;
    private static final String bindingErrorMessage;
    private static final int[] bindingNamespaceIndexes;
    private static final String[] bindingNamespacePrefixes;
    
          private static final String _type_name0;
  
    static {
        org.jibx.runtime.IBindingFactory factory = null;
        String message = null;
        try {
    
            factory = org.jibx.runtime.BindingDirectory.getFactory("binding", "com.sosnoski.infoq.ex1", StoreServiceStub.class.getClassLoader());
      
            message = null;
        } catch (Exception e) { message = e.getMessage(); }
        bindingFactory = factory;
        bindingErrorMessage = message;
    
         _type_name0 =
             "{http://ws.sosnoski.com/order/data}:order";
  
        int[] indexes = null;
        String[] prefixes = null;
        if (factory != null) {
            
            // check for xsi namespace included
            String[] nsuris = factory.getNamespaces();
            int xsiindex = nsuris.length;
            while (--xsiindex >= 0 &&
                !"http://www.w3.org/2001/XMLSchema-instance".equals(nsuris[xsiindex]));
            
            // get actual size of index and prefix arrays to be allocated
            int nscount = 2;
            int usecount = nscount;
            if (xsiindex >= 0) {
                usecount++;
            }
            
            // allocate and initialize the arrays
            indexes = new int[usecount];
            prefixes = new String[usecount];
      
            indexes[0] = nsIndex("http://ws.sosnoski.com/order/data", nsuris);
            prefixes[0] = "";
      
            indexes[1] = nsIndex("http://ws.sosnoski.com/order/wsdl", nsuris);
            prefixes[1] = "ns1";
      
            if (xsiindex >= 0) {
                indexes[nscount] = xsiindex;
                prefixes[nscount] = "xsi";
            }
            
        }
        bindingNamespaceIndexes = indexes;
        bindingNamespacePrefixes = prefixes;
    }
    
    private static int nsIndex(String uri, String[] uris) {
        for (int i = 0; i < uris.length; i++) {
            if (uri.equals(uris[i])) {
                return i;
            }
        }
        throw new IllegalArgumentException("Namespace " + uri + " not found in binding directory information");
    }
    
    private static void addMappingNamespaces(org.apache.axiom.soap.SOAPFactory factory, org.apache.axiom.om.OMElement wrapper, String nsuri, String nspref) {
        String[] nss = bindingFactory.getNamespaces();
        for (int i = 0; i < bindingNamespaceIndexes.length; i++) {
            int index = bindingNamespaceIndexes[i];
            String uri = nss[index];
            String prefix = bindingNamespacePrefixes[i];
            if (!nsuri.equals(uri) || !nspref.equals(prefix)) {
                wrapper.declareNamespace(factory.createOMNamespace(uri, prefix));
            }
        }
    }
    
    private static org.jibx.runtime.impl.UnmarshallingContext getNewUnmarshalContext(org.apache.axiom.om.OMElement param)
        throws org.jibx.runtime.JiBXException {
        if (bindingFactory == null) {
            throw new RuntimeException(bindingErrorMessage);
        }
        org.jibx.runtime.impl.UnmarshallingContext ctx =
            (org.jibx.runtime.impl.UnmarshallingContext)bindingFactory.createUnmarshallingContext();
        org.jibx.runtime.IXMLReader reader = new org.jibx.runtime.impl.StAXReaderWrapper(param.getXMLStreamReaderWithoutCaching(), "SOAP-message", true);
        ctx.setDocument(reader);
        ctx.toTag();
        return ctx;
    }
    
    private org.apache.axiom.om.OMElement mappedChild(Object value, org.apache.axiom.om.OMFactory factory) {
        org.jibx.runtime.IMarshallable mrshable = (org.jibx.runtime.IMarshallable)value;
        org.apache.axiom.om.OMDataSource src = new org.apache.axis2.jibx.JiBXDataSource(mrshable, bindingFactory);
        int index = bindingFactory.getClassIndexMap().get(mrshable.JiBX_getName());
        org.apache.axiom.om.OMNamespace appns = factory.createOMNamespace(bindingFactory.getElementNamespaces()[index], "");
        return factory.createOMElement(src, bindingFactory.getElementNames()[index], appns);
    }
    
    
    private static Object fromOM(org.apache.axiom.om.OMElement param, Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{
        try {
            org.jibx.runtime.impl.UnmarshallingContext ctx = getNewUnmarshalContext(param);
            return ctx.unmarshalElement(type);
        } catch (Exception e) {
             throw new org.apache.axis2.AxisFault(e.getMessage());
        }
    }
  
    
        /**
    
         * Auto generated synchronous call method
         * 
         * @see com.sosnoski.infoq.ex1.StoreService#retrieveOrder
         * @param id
         */
        public com.sosnoski.infoq.ex1.Order retrieveOrder(
    java.lang.String id
            ) throws java.rmi.RemoteException
    
            {
    
            try {
                int _opIndex = 0;
                javax.xml.namespace.QName opname = _operations[_opIndex].getName();
                org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(opname);
                _operationClient.getOptions().setAction("urn:retrieveOrder");
                _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);
    
                // create SOAP envelope with the payload
                org.apache.axiom.soap.SOAPEnvelope env = createEnvelope(_operationClient.getOptions());
                org.apache.axiom.soap.SOAPFactory factory = getFactory(_operationClient.getOptions().getSoapVersionURI());
                org.apache.axiom.om.OMElement wrapper = factory.createOMElement("retrieveOrder", "http://ws.sosnoski.com/order/wsdl", "");
    
                wrapper.declareDefaultNamespace("http://ws.sosnoski.com/order/wsdl");
    
                wrapper.declareNamespace(factory.createOMNamespace("http://ws.sosnoski.com/order/wsdl", ""));
    
                env.getBody().addChild(wrapper);
                org.apache.axiom.om.OMElement child;
    
        if (id == null) {
        
            // just skip optional element
          
        } else {
        
        child = factory.createOMElement("id", "http://ws.sosnoski.com/order/wsdl", "");
        child.setText(id);
      
        wrapper.addChild(child);
  
        }
      
    
                // add SOAP headers
                _serviceClient.addHeadersToEnvelope(env);
                
                // create message context with that envelope
                org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();
                _messageContext.setEnvelope(env);
    
                // add the message context to the operation client
                _operationClient.addMessageContext(_messageContext);
    
    
               // execute the operation client
                _operationClient.execute(true);
                
      
                org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                    .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.om.OMElement _response = _returnMessageContext.getEnvelope().getBody().getFirstElement();
                if (_response != null && "retrieveOrderResponse".equals(_response.getLocalName()) &&
                    "http://ws.sosnoski.com/order/wsdl".equals(_response.getNamespace().getNamespaceURI())) {
                    org.jibx.runtime.impl.UnmarshallingContext uctx = getNewUnmarshalContext(_response);
                    uctx.parsePastStartTag("http://ws.sosnoski.com/order/wsdl", "retrieveOrderResponse");
                    int index;
        com.sosnoski.infoq.ex1.Order return0 = null;
    
            if (uctx.isAt("http://ws.sosnoski.com/order/wsdl", "return")) {
      return0 = (com.sosnoski.infoq.ex1.Order)
        uctx.getUnmarshaller(_type_name0).unmarshal(new com.sosnoski.infoq.ex1.Order(), uctx)
      ;
    
                uctx.parsePastCurrentEndTag("http://ws.sosnoski.com/order/wsdl", "return");
    
            }
      
                    return return0;
                } else {
                    throw new org.apache.axis2.AxisFault("Missing expected return wrapper element {http://ws.sosnoski.com/order/wsdl}retrieveOrderResponse");
                }
      
            } catch (Exception e) {
                Exception outex = convertException(e);
      
                // should never happen, but just in case
                throw new RuntimeException("Unexpected exception type: " +
                    outex.getClass().getName(), outex);
            }
        }
    
    
        /**
    
         * Auto generated synchronous call method
         * 
         * @see com.sosnoski.infoq.ex1.StoreService#placeOrder
         * @param order
         */
        public java.lang.String placeOrder(
    com.sosnoski.infoq.ex1.Order order
            ) throws java.rmi.RemoteException
    
            {
    
            try {
                int _opIndex = 1;
                javax.xml.namespace.QName opname = _operations[_opIndex].getName();
                org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(opname);
                _operationClient.getOptions().setAction("urn:placeOrder");
                _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);
    
                // create SOAP envelope with the payload
                org.apache.axiom.soap.SOAPEnvelope env = createEnvelope(_operationClient.getOptions());
                org.apache.axiom.soap.SOAPFactory factory = getFactory(_operationClient.getOptions().getSoapVersionURI());
                org.apache.axiom.om.OMElement wrapper = factory.createOMElement("placeOrder", "http://ws.sosnoski.com/order/wsdl", "ns1");
    
                addMappingNamespaces(factory, wrapper, "http://ws.sosnoski.com/order/wsdl", "ns1");
    
                env.getBody().addChild(wrapper);
                org.apache.axiom.om.OMElement child;
    
        if (order == null) {
        
            // just skip optional element
          
        } else {
        
        if (bindingFactory == null) {
            throw new RuntimeException(bindingErrorMessage);
        }
        org.apache.axiom.om.OMDataSource src = new org.apache.axis2.jibx.JiBXDataSource(order, _type_name0, "order", "http://ws.sosnoski.com/order/wsdl", "ns1", bindingNamespaceIndexes, bindingNamespacePrefixes, bindingFactory);
        org.apache.axiom.om.OMNamespace appns = factory.createOMNamespace("http://ws.sosnoski.com/order/wsdl", "");
        child = factory.createOMElement(src, "order", appns);
      
        wrapper.addChild(child);
  
        }
      
    
                // add SOAP headers
                _serviceClient.addHeadersToEnvelope(env);
                
                // create message context with that envelope
                org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();
                _messageContext.setEnvelope(env);
    
                // add the message context to the operation client
                _operationClient.addMessageContext(_messageContext);
    
    
               // execute the operation client
                _operationClient.execute(true);
                
      
                org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                    .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.om.OMElement _response = _returnMessageContext.getEnvelope().getBody().getFirstElement();
                if (_response != null && "placeOrderResponse".equals(_response.getLocalName()) &&
                    "http://ws.sosnoski.com/order/wsdl".equals(_response.getNamespace().getNamespaceURI())) {
                    org.jibx.runtime.impl.UnmarshallingContext uctx = getNewUnmarshalContext(_response);
                    uctx.parsePastStartTag("http://ws.sosnoski.com/order/wsdl", "placeOrderResponse");
                    int index;
        java.lang.String id = null;
    
            if (uctx.isAt("http://ws.sosnoski.com/order/wsdl", "id")) {
      id = (java.lang.String)
        uctx.parseElementText("http://ws.sosnoski.com/order/wsdl", "id")
      ;
    
            }
      
                    return id;
                } else {
                    throw new org.apache.axis2.AxisFault("Missing expected return wrapper element {http://ws.sosnoski.com/order/wsdl}placeOrderResponse");
                }
      
            } catch (Exception e) {
                Exception outex = convertException(e);
      
                // should never happen, but just in case
                throw new RuntimeException("Unexpected exception type: " +
                    outex.getClass().getName(), outex);
            }
        }
    
    
        /**
    
         * Auto generated synchronous call method
         * 
         * @see com.sosnoski.infoq.ex1.StoreService#cancelOrder
         * @param id
         */
        public boolean cancelOrder(
    java.lang.String id
            ) throws java.rmi.RemoteException
    
            {
    
            try {
                int _opIndex = 2;
                javax.xml.namespace.QName opname = _operations[_opIndex].getName();
                org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(opname);
                _operationClient.getOptions().setAction("urn:cancelOrder");
                _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);
    
                // create SOAP envelope with the payload
                org.apache.axiom.soap.SOAPEnvelope env = createEnvelope(_operationClient.getOptions());
                org.apache.axiom.soap.SOAPFactory factory = getFactory(_operationClient.getOptions().getSoapVersionURI());
                org.apache.axiom.om.OMElement wrapper = factory.createOMElement("cancelOrder", "http://ws.sosnoski.com/order/wsdl", "");
    
                wrapper.declareDefaultNamespace("http://ws.sosnoski.com/order/wsdl");
    
                wrapper.declareNamespace(factory.createOMNamespace("http://ws.sosnoski.com/order/wsdl", ""));
    
                env.getBody().addChild(wrapper);
                org.apache.axiom.om.OMElement child;
    
        if (id == null) {
        
            // just skip optional element
          
        } else {
        
        child = factory.createOMElement("id", "http://ws.sosnoski.com/order/wsdl", "");
        child.setText(id);
      
        wrapper.addChild(child);
  
        }
      
    
                // add SOAP headers
                _serviceClient.addHeadersToEnvelope(env);
                
                // create message context with that envelope
                org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();
                _messageContext.setEnvelope(env);
    
                // add the message context to the operation client
                _operationClient.addMessageContext(_messageContext);
    
    
               // execute the operation client
                _operationClient.execute(true);
                
      
                org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                    .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.om.OMElement _response = _returnMessageContext.getEnvelope().getBody().getFirstElement();
                if (_response != null && "cancelOrderResponse".equals(_response.getLocalName()) &&
                    "http://ws.sosnoski.com/order/wsdl".equals(_response.getNamespace().getNamespaceURI())) {
                    org.jibx.runtime.impl.UnmarshallingContext uctx = getNewUnmarshalContext(_response);
                    uctx.parsePastStartTag("http://ws.sosnoski.com/order/wsdl", "cancelOrderResponse");
                    int index;
        boolean return0 = false;
    
            if (uctx.isAt("http://ws.sosnoski.com/order/wsdl", "return")) {
      return0 = (boolean)org.jibx.runtime.Utility.parseBoolean(uctx.parseElementText("http://ws.sosnoski.com/order/wsdl", "return"))
      ;
    
            } else {
                throw new org.apache.axis2.AxisFault("Missing required element {http://ws.sosnoski.com/order/wsdl}return");
            }
      
                    return return0;
                } else {
                    throw new org.apache.axis2.AxisFault("Missing expected return wrapper element {http://ws.sosnoski.com/order/wsdl}cancelOrderResponse");
                }
      
            } catch (Exception e) {
                Exception outex = convertException(e);
      
                // should never happen, but just in case
                throw new RuntimeException("Unexpected exception type: " +
                    outex.getClass().getName(), outex);
            }
        }
    
    
        /**
    
         * Auto generated asynchronous call method
         * 
         * @see com.sosnoski.infoq.ex1.StoreService#startretrieveOrder
         * @param id
         */
        public void startretrieveOrder(
    java.lang.String id, final StoreServiceCallbackHandler _callback
    
            ) throws java.rmi.RemoteException
    
            {
    
            try {
                int _opIndex = 0;
                javax.xml.namespace.QName opname = _operations[_opIndex].getName();
                org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(opname);
                _operationClient.getOptions().setAction("urn:retrieveOrder");
                _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);
    
                // create SOAP envelope with the payload
                org.apache.axiom.soap.SOAPEnvelope env = createEnvelope(_operationClient.getOptions());
                org.apache.axiom.soap.SOAPFactory factory = getFactory(_operationClient.getOptions().getSoapVersionURI());
                org.apache.axiom.om.OMElement wrapper = factory.createOMElement("retrieveOrder", "http://ws.sosnoski.com/order/wsdl", "");
    
                wrapper.declareDefaultNamespace("http://ws.sosnoski.com/order/wsdl");
    
                wrapper.declareNamespace(factory.createOMNamespace("http://ws.sosnoski.com/order/wsdl", ""));
    
                env.getBody().addChild(wrapper);
                org.apache.axiom.om.OMElement child;
    
        if (id == null) {
        
            // just skip optional element
          
        } else {
        
        child = factory.createOMElement("id", "http://ws.sosnoski.com/order/wsdl", "");
        child.setText(id);
      
        wrapper.addChild(child);
  
        }
      
    
                // add SOAP headers
                _serviceClient.addHeadersToEnvelope(env);
                
                // create message context with that envelope
                org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();
                _messageContext.setEnvelope(env);
    
                // add the message context to the operation client
                _operationClient.addMessageContext(_messageContext);
    
    
                _operationClient.setCallback(new org.apache.axis2.client.async.Callback() {
                    public void onComplete(org.apache.axis2.client.async.AsyncResult async) {
                        try {
                            org.apache.axiom.om.OMElement result = async.getResponseEnvelope().getBody().getFirstElement();
                            if (result != null && "retrieveOrderResponse".equals(result.getLocalName()) &&
                                "http://ws.sosnoski.com/order/wsdl".equals(result.getNamespace().getNamespaceURI())) {
                                org.jibx.runtime.impl.UnmarshallingContext uctx = getNewUnmarshalContext(result);
                                uctx.parsePastStartTag("http://ws.sosnoski.com/order/wsdl", "retrieveOrderResponse");
                                int index;
      com.sosnoski.infoq.ex1.Order return0 = null;
    
            if (uctx.isAt("http://ws.sosnoski.com/order/wsdl", "return")) {
      return0 = (com.sosnoski.infoq.ex1.Order)
        uctx.getUnmarshaller(_type_name0).unmarshal(new com.sosnoski.infoq.ex1.Order(), uctx)
      ;
    
                uctx.parsePastCurrentEndTag("http://ws.sosnoski.com/order/wsdl", "return");
    
            }
      
                                _callback.receiveResultretrieveOrder(return0);
                            } else {
                                throw new org.apache.axis2.AxisFault("Missing expected result wrapper element {http://ws.sosnoski.com/order/wsdl}retrieveOrderResponse");
                            }
                        } catch (Exception e) {
                            onError(e);
                        }
                    }

                    public void onError(Exception e) {
                        _callback.receiveErrorretrieveOrder(e);
                    }
                });
                        
                org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
                if ( _operations[_opIndex].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
                    _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
                    _operations[_opIndex].setMessageReceiver(_callbackReceiver);
                }

                // execute the operation client
                _operationClient.execute(false);
                
            } catch (Exception e) {
                Exception outex = convertException(e);
                throw new RuntimeException("Unexpected exception type: " +
                    outex.getClass().getName(), outex);
            }
        }
    
    
        /**
    
         * Auto generated asynchronous call method
         * 
         * @see com.sosnoski.infoq.ex1.StoreService#startplaceOrder
         * @param order
         */
        public void startplaceOrder(
    com.sosnoski.infoq.ex1.Order order, final StoreServiceCallbackHandler _callback
    
            ) throws java.rmi.RemoteException
    
            {
    
            try {
                int _opIndex = 1;
                javax.xml.namespace.QName opname = _operations[_opIndex].getName();
                org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(opname);
                _operationClient.getOptions().setAction("urn:placeOrder");
                _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);
    
                // create SOAP envelope with the payload
                org.apache.axiom.soap.SOAPEnvelope env = createEnvelope(_operationClient.getOptions());
                org.apache.axiom.soap.SOAPFactory factory = getFactory(_operationClient.getOptions().getSoapVersionURI());
                org.apache.axiom.om.OMElement wrapper = factory.createOMElement("placeOrder", "http://ws.sosnoski.com/order/wsdl", "ns1");
    
                addMappingNamespaces(factory, wrapper, "http://ws.sosnoski.com/order/wsdl", "ns1");
    
                env.getBody().addChild(wrapper);
                org.apache.axiom.om.OMElement child;
    
        if (order == null) {
        
            // just skip optional element
          
        } else {
        
        if (bindingFactory == null) {
            throw new RuntimeException(bindingErrorMessage);
        }
        org.apache.axiom.om.OMDataSource src = new org.apache.axis2.jibx.JiBXDataSource(order, _type_name0, "order", "http://ws.sosnoski.com/order/wsdl", "ns1", bindingNamespaceIndexes, bindingNamespacePrefixes, bindingFactory);
        org.apache.axiom.om.OMNamespace appns = factory.createOMNamespace("http://ws.sosnoski.com/order/wsdl", "");
        child = factory.createOMElement(src, "order", appns);
      
        wrapper.addChild(child);
  
        }
      
    
                // add SOAP headers
                _serviceClient.addHeadersToEnvelope(env);
                
                // create message context with that envelope
                org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();
                _messageContext.setEnvelope(env);
    
                // add the message context to the operation client
                _operationClient.addMessageContext(_messageContext);
    
    
                _operationClient.setCallback(new org.apache.axis2.client.async.Callback() {
                    public void onComplete(org.apache.axis2.client.async.AsyncResult async) {
                        try {
                            org.apache.axiom.om.OMElement result = async.getResponseEnvelope().getBody().getFirstElement();
                            if (result != null && "placeOrderResponse".equals(result.getLocalName()) &&
                                "http://ws.sosnoski.com/order/wsdl".equals(result.getNamespace().getNamespaceURI())) {
                                org.jibx.runtime.impl.UnmarshallingContext uctx = getNewUnmarshalContext(result);
                                uctx.parsePastStartTag("http://ws.sosnoski.com/order/wsdl", "placeOrderResponse");
                                int index;
      java.lang.String id = null;
    
            if (uctx.isAt("http://ws.sosnoski.com/order/wsdl", "id")) {
      id = (java.lang.String)
        uctx.parseElementText("http://ws.sosnoski.com/order/wsdl", "id")
      ;
    
            }
      
                                _callback.receiveResultplaceOrder(id);
                            } else {
                                throw new org.apache.axis2.AxisFault("Missing expected result wrapper element {http://ws.sosnoski.com/order/wsdl}placeOrderResponse");
                            }
                        } catch (Exception e) {
                            onError(e);
                        }
                    }

                    public void onError(Exception e) {
                        _callback.receiveErrorplaceOrder(e);
                    }
                });
                        
                org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
                if ( _operations[_opIndex].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
                    _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
                    _operations[_opIndex].setMessageReceiver(_callbackReceiver);
                }

                // execute the operation client
                _operationClient.execute(false);
                
            } catch (Exception e) {
                Exception outex = convertException(e);
                throw new RuntimeException("Unexpected exception type: " +
                    outex.getClass().getName(), outex);
            }
        }
    
    
        /**
    
         * Auto generated asynchronous call method
         * 
         * @see com.sosnoski.infoq.ex1.StoreService#startcancelOrder
         * @param id
         */
        public void startcancelOrder(
    java.lang.String id, final StoreServiceCallbackHandler _callback
    
            ) throws java.rmi.RemoteException
    
            {
    
            try {
                int _opIndex = 2;
                javax.xml.namespace.QName opname = _operations[_opIndex].getName();
                org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(opname);
                _operationClient.getOptions().setAction("urn:cancelOrder");
                _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);
    
                // create SOAP envelope with the payload
                org.apache.axiom.soap.SOAPEnvelope env = createEnvelope(_operationClient.getOptions());
                org.apache.axiom.soap.SOAPFactory factory = getFactory(_operationClient.getOptions().getSoapVersionURI());
                org.apache.axiom.om.OMElement wrapper = factory.createOMElement("cancelOrder", "http://ws.sosnoski.com/order/wsdl", "");
    
                wrapper.declareDefaultNamespace("http://ws.sosnoski.com/order/wsdl");
    
                wrapper.declareNamespace(factory.createOMNamespace("http://ws.sosnoski.com/order/wsdl", ""));
    
                env.getBody().addChild(wrapper);
                org.apache.axiom.om.OMElement child;
    
        if (id == null) {
        
            // just skip optional element
          
        } else {
        
        child = factory.createOMElement("id", "http://ws.sosnoski.com/order/wsdl", "");
        child.setText(id);
      
        wrapper.addChild(child);
  
        }
      
    
                // add SOAP headers
                _serviceClient.addHeadersToEnvelope(env);
                
                // create message context with that envelope
                org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();
                _messageContext.setEnvelope(env);
    
                // add the message context to the operation client
                _operationClient.addMessageContext(_messageContext);
    
    
                _operationClient.setCallback(new org.apache.axis2.client.async.Callback() {
                    public void onComplete(org.apache.axis2.client.async.AsyncResult async) {
                        try {
                            org.apache.axiom.om.OMElement result = async.getResponseEnvelope().getBody().getFirstElement();
                            if (result != null && "cancelOrderResponse".equals(result.getLocalName()) &&
                                "http://ws.sosnoski.com/order/wsdl".equals(result.getNamespace().getNamespaceURI())) {
                                org.jibx.runtime.impl.UnmarshallingContext uctx = getNewUnmarshalContext(result);
                                uctx.parsePastStartTag("http://ws.sosnoski.com/order/wsdl", "cancelOrderResponse");
                                int index;
      boolean return0 = false;
    
            if (uctx.isAt("http://ws.sosnoski.com/order/wsdl", "return")) {
      return0 = (boolean)org.jibx.runtime.Utility.parseBoolean(uctx.parseElementText("http://ws.sosnoski.com/order/wsdl", "return"))
      ;
    
            } else {
                throw new org.apache.axis2.AxisFault("Missing required element {http://ws.sosnoski.com/order/wsdl}return");
            }
      
                                _callback.receiveResultcancelOrder(return0);
                            } else {
                                throw new org.apache.axis2.AxisFault("Missing expected result wrapper element {http://ws.sosnoski.com/order/wsdl}cancelOrderResponse");
                            }
                        } catch (Exception e) {
                            onError(e);
                        }
                    }

                    public void onError(Exception e) {
                        _callback.receiveErrorcancelOrder(e);
                    }
                });
                        
                org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
                if ( _operations[_opIndex].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
                    _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
                    _operations[_opIndex].setMessageReceiver(_callbackReceiver);
                }

                // execute the operation client
                _operationClient.execute(false);
                
            } catch (Exception e) {
                Exception outex = convertException(e);
                throw new RuntimeException("Unexpected exception type: " +
                    outex.getClass().getName(), outex);
            }
        }
    
    
    private Exception convertException(Exception ex) throws java.rmi.RemoteException {
        if (ex instanceof org.apache.axis2.AxisFault) {
            org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault)ex;
            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
                    try {
                        
                        // first create the actual exception
                        String exceptionClassName = (String)faultExceptionClassNameMap.get(faultElt.getQName());
                        Class exceptionClass = Class.forName(exceptionClassName);
                        Exception e = (Exception)exceptionClass.newInstance();
                        
                        // build the message object from the details
                        String messageClassName = (String)faultMessageMap.get(faultElt.getQName());
                        Class messageClass = Class.forName(messageClassName);
                        Object messageObject = fromOM(faultElt, messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                            new Class[] { messageClass });
                        m.invoke(e, new Object[] { messageObject });
                        return e;
                        
                    } catch (ClassCastException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    } catch (InstantiationException e) {
                        // we cannot intantiate the class - throw the original
                        // Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
            
        } else if (ex instanceof RuntimeException) {
            throw (RuntimeException)ex;
        } else if (ex instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)ex;
        } else {
            throw org.apache.axis2.AxisFault.makeFault(ex);
        }
    }
    
  
   }
   