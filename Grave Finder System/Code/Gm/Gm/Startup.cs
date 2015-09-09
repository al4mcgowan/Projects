using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Gm.Startup))]
namespace Gm
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
