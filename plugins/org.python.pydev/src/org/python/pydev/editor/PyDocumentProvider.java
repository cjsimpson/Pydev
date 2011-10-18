/**
 * Copyright (c) 2005-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Eclipse Public License (EPL).
 * Please see the license.txt included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
/*
 * Created on 25/06/2005
 */
package org.python.pydev.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.python.pydev.editorinput.PydevZipFileEditorInput;

/**
 * @author Fabio
 */
public class PyDocumentProvider extends TextFileDocumentProvider{
    
    private Map<Object, IAnnotationModel> moreInfo = new HashMap<Object, IAnnotationModel>();
    
    @Override
    public void connect(Object element) throws CoreException {
        
        super.connect(element);
        if (getFileInfo(element) != null)
            return;
        
        if(element instanceof PydevZipFileEditorInput){
            moreInfo.put(element, new PyExternalZipFileAnnotationModel((PydevZipFileEditorInput)element));
        }

    }
    
    @Override
    public IAnnotationModel getAnnotationModel(Object element) {
        IAnnotationModel annotationModel = super.getAnnotationModel(element);
        if(annotationModel == null){
            annotationModel = moreInfo.get(element);
        }
        return annotationModel;
    }

}
