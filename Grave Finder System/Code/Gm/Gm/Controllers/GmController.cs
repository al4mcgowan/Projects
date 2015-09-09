using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Gm.Models;

namespace Gm.Controllers
{
    public class GmController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: Gm
        public ActionResult Index(string searchString)
        {
            if (!String.IsNullOrEmpty(searchString))
            {
                var graves = from g in db.Graves select g;
                graves = graves.Where(g => g.Cemetery.Contains(searchString)
                                      || g.Name.Contains(searchString));
                return View(graves.ToList());
            }
            return View(db.Graves.ToList());
        }

        // GET: Gm/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Grave grave = db.Graves.Find(id);
            if (grave == null)
            {
                return HttpNotFound();
            }
            return View(grave);
        }

        // GET: Gm/Create
        [Authorize(Roles = "canEdit")]
        public ActionResult Create()
        {
            return View();
        }

        // POST: Gm/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "canEdit")]
        public ActionResult Create([Bind(Include = "GraveID,Cemetery,RowID,GraveNumber,Name,Gender,Address,DOB,DOD,InGrave,Longitude,Latitude")] Grave grave)
        {
            if (ModelState.IsValid)
            {
                db.Graves.Add(grave);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(grave);
        }

        // GET: Gm/Edit/5
        [Authorize(Roles = "canEdit")]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Grave grave = db.Graves.Find(id);
            if (grave == null)
            {
                return HttpNotFound();
            }
            return View(grave);
        }

        // POST: Gm/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "GraveID,Cemetery,RowID,GraveNumber,Name,Gender,Address,DOB,DOD,InGrave,Longitude,Latitude")] Grave grave)
        {
            if (ModelState.IsValid)
            {
                db.Entry(grave).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(grave);
        }

        // GET: Gm/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Grave grave = db.Graves.Find(id);
            if (grave == null)
            {
                return HttpNotFound();
            }
            return View(grave);
        }

        // POST: Gm/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Grave grave = db.Graves.Find(id);
            db.Graves.Remove(grave);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
