
/**
 * StoreServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */
        package com.sosnoski.infoq.ex1;

        /**
        *  StoreServiceMessageReceiverInOut message receiver
        */

        public class StoreServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        StoreServiceSkeleton skel = (StoreServiceSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){

        

            if("retrieveOrder".equals(methodName)){
                
                
                        envelope = jibxReceiver0(msgContext.getEnvelope().getBody().getFirstElement(), skel, getSOAPFactory(msgContext));
                    } else 

            if("placeOrder".equals(methodName)){
                
                
                        envelope = jibxReceiver1(msgContext.getEnvelope().getBody().getFirstElement(), skel, getSOAPFactory(msgContext));
                    } else 

            if("cancelOrder".equals(methodName)){
                
                
                        envelope = jibxReceiver2(msgContext.getEnvelope().getBody().getFirstElement(), skel, getSOAPFactory(msgContext));
                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
    private static final org.jibx.runtime.IBindingFactory bindingFactory;
    private static final String bindingErrorMessage;
    private static final int[] bindingNamespaceIndexes;
    private static final String[] bindingNamespacePrefixes;
    
          private static final String _type_name0;
  
    static {
        org.jibx.runtime.IBindingFactory factory = null;
        String message = null;
        try {
    
            factory = org.jibx.runtime.BindingDirectory.getFactory("binding", "com.sosnoski.infoq.ex1", StoreServiceMessageReceiverInOut.class.getClassLoader());
      
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
  
      public org.apache.axiom.soap.SOAPEnvelope jibxReceiver0(org.apache.axiom.om.OMElement element, StoreServiceSkeleton skel, org.apache.axiom.soap.SOAPFactory factory) throws org.apache.axis2.AxisFault
      
      {
          org.apache.axiom.soap.SOAPEnvelope envelope = null;
          try {
              org.jibx.runtime.impl.UnmarshallingContext uctx = getNewUnmarshalContext(element);
              uctx.next();
              int index;
    java.lang.String id = null;
    
            if (uctx.isAt("http://ws.sosnoski.com/order/wsdl", "id")) {
      id = (java.lang.String)
        uctx.parseElementText("http://ws.sosnoski.com/order/wsdl", "id")
      ;
    
            }
      
              envelope = factory.getDefaultEnvelope();
              org.apache.axiom.om.OMElement wrapper = factory.createOMElement("retrieveOrderResponse", "http://ws.sosnoski.com/order/wsdl", "ns1");
        
              addMappingNamespaces(factory, wrapper, "http://ws.sosnoski.com/order/wsdl", "ns1");
        
              envelope.getBody().addChild(wrapper);
              com.sosnoski.infoq.ex1.Order result = skel.retrieveOrder(
    id
    )
  ;
        
              if (result == null) {
            
                          // just skip optional element
              
              } else {
                  if (bindingFactory == null) {
                      throw new RuntimeException(bindingErrorMessage);
                  }
            
                  org.apache.axiom.om.OMDataSource src = new org.apache.axis2.jibx.JiBXDataSource(result, _type_name0, "return", "http://ws.sosnoski.com/order/wsdl", "ns1", bindingNamespaceIndexes, bindingNamespacePrefixes, bindingFactory);
                  org.apache.axiom.om.OMNamespace appns = factory.createOMNamespace("http://ws.sosnoski.com/order/wsdl", "");
                  org.apache.axiom.om.OMElement child = factory.createOMElement(src, "return", appns);
                  wrapper.addChild(child);
              
              }
          
          } catch (org.jibx.runtime.JiBXException e) {
              throw org.apache.axis2.AxisFault.makeFault(e);
          }
          return envelope;
      }
  
      public org.apache.axiom.soap.SOAPEnvelope jibxReceiver1(org.apache.axiom.om.OMElement element, StoreServiceSkeleton skel, org.apache.axiom.soap.SOAPFactory factory) throws org.apache.axis2.AxisFault
      
      {
          org.apache.axiom.soap.SOAPEnvelope envelope = null;
          try {
              org.jibx.runtime.impl.UnmarshallingContext uctx = getNewUnmarshalContext(element);
              uctx.next();
              int index;
    com.sosnoski.infoq.ex1.Order order = null;
    
            if (uctx.isAt("http://ws.sosnoski.com/order/wsdl", "order")) {
      order = (com.sosnoski.infoq.ex1.Order)
        uctx.getUnmarshaller(_type_name0).unmarshal(new com.sosnoski.infoq.ex1.Order(), uctx)
      ;
    
                uctx.parsePastCurrentEndTag("http://ws.sosnoski.com/order/wsdl", "order");
    
            }
      
              envelope = factory.getDefaultEnvelope();
              org.apache.axiom.om.OMElement wrapper = factory.createOMElement("placeOrderResponse", "http://ws.sosnoski.com/order/wsdl", "");
        
            wrapper.declareDefaultNamespace("http://ws.sosnoski.com/order/wsdl");
        
              wrapper.declareNamespace(factory.createOMNamespace("http://ws.sosnoski.com/order/wsdl", ""));
        
              envelope.getBody().addChild(wrapper);
              java.lang.String result = skel.placeOrder(
    order
    )
  ;
        
              org.apache.axiom.om.OMElement child = factory.createOMElement("id",  "http://ws.sosnoski.com/order/wsdl", "");
            
              if (result == null) {
                  org.apache.axiom.om.OMNamespace xsins = factory.createOMNamespace("http://www.w3.org/2001/XMLSchema-instance", "xsi");
                  child.declareNamespace(xsins);
                  child.addAttribute("nil", "true", xsins);
              } else {
                
              child.setText(result.toString());
      
              }
              
              wrapper.addChild(child);
          
          } catch (org.jibx.runtime.JiBXException e) {
              throw org.apache.axis2.AxisFault.makeFault(e);
          }
          return envelope;
      }
  
      public org.apache.axiom.soap.SOAPEnvelope jibxReceiver2(org.apache.axiom.om.OMElement element, StoreServiceSkeleton skel, org.apache.axiom.soap.SOAPFactory factory) throws org.apache.axis2.AxisFault
      
      {
          org.apache.axiom.soap.SOAPEnvelope envelope = null;
          try {
              org.jibx.runtime.impl.UnmarshallingContext uctx = getNewUnmarshalContext(element);
              uctx.next();
              int index;
    java.lang.String id = null;
    
            if (uctx.isAt("http://ws.sosnoski.com/order/wsdl", "id")) {
      id = (java.lang.String)
        uctx.parseElementText("http://ws.sosnoski.com/order/wsdl", "id")
      ;
    
            }
      
              envelope = factory.getDefaultEnvelope();
              org.apache.axiom.om.OMElement wrapper = factory.createOMElement("cancelOrderResponse", "http://ws.sosnoski.com/order/wsdl", "");
        
            wrapper.declareDefaultNamespace("http://ws.sosnoski.com/order/wsdl");
        
              wrapper.declareNamespace(factory.createOMNamespace("http://ws.sosnoski.com/order/wsdl", ""));
        
              envelope.getBody().addChild(wrapper);
              boolean result = skel.cancelOrder(
    id
    )
  ;
        
              org.apache.axiom.om.OMElement child = factory.createOMElement("return",  "http://ws.sosnoski.com/order/wsdl", "");
            
              child.setText(org.jibx.runtime.Utility.serializeBoolean(result));
      
              wrapper.addChild(child);
          
          } catch (org.jibx.runtime.JiBXException e) {
              throw org.apache.axis2.AxisFault.makeFault(e);
          }
          return envelope;
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

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    