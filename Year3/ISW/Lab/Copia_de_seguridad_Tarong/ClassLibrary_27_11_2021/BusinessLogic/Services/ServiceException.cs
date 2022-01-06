using System;
using System.Runtime.Serialization;

namespace TarongISW.Services
{

    [Serializable()]
    public class ServiceException : Exception
    {
        //to get the error message (for displaying in the GUI for example),
        //access the Message property in this object
        public ServiceException(String msg) : base(msg) {  }
    }
}
