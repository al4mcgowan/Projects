using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using Gm.Models;

namespace Gm.Controllers
{
    public class GravesController : ApiController
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: api/Graves
        public IEnumerable<Grave> GetGraves()
        {
            return db.Graves;
        }
    }
}