package com.emusic.controller.util;

import com.emusic.controller.exception.ResourceNotFoundException;

public final class RestPreconditions {
    private RestPreconditions() {
        throw new AssertionError();
    }
    
    public static void checkFound(final boolean expression) {
    	checkFound(expression, null);
    }
    
    public static void checkFound(final boolean expression, String errorMsg) {
        if (!expression) {
        	if(errorMsg == null) {
        		throw new ResourceNotFoundException();
        	} else {
        		throw new ResourceNotFoundException(errorMsg);
        	}
        }
    }
    public static <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException();
        }

        return resource;
    }
    
}
