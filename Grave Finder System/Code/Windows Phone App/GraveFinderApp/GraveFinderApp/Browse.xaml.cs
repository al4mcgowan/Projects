using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web.Http;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Navigation;

// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkID=390556

namespace GraveFinderApp
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class Browse : Page
    {
        public Browse()
        {
            this.InitializeComponent();
        }

        /// <summary>
        /// Invoked when this page is about to be displayed in a Frame.
        /// </summary>
        /// <param name="e">Event data that describes how this page was reached.
        /// This parameter is typically used to configure the page.</param>
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            ReadData();
        }

        public async void ReadData()
        {
            try
            {
                using(HttpClient client = new HttpClient())
                {
                    client.BaseAddress = new Uri("https://gmanager.azurewebsites.net/");
                    client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                    // GET ../api/Graves/
                    HttpResponseMessage response = await client.GetAsync("api/Graves");
                    if (response.IsSuccessStatusCode)
                    {
                        var graves = await response.Content.ReadAsAsync<IEnumerable<Grave>>();
                        foreach (var grave in graves)
                        {
                            ListOfGraves.Items.Add("Name: " + grave.Name + "\nGender: " + grave.Gender + "\nCemetery: " + grave.Cemetery
                                                    + "\nLast Address: " + grave.Address + "\nDate of Birth: " + grave.DOB.Date.ToString("dd/MM/yyyy")
                                                    + "\nDate of Death: " + grave.DOD.Date.ToString("dd/MM/yyyy") + "\nOthers in grave: " + grave.InGrave + "\n");
                            ListOfGraves.SelectionMode = ListViewSelectionMode.None;
                            ListOfGraves.IsItemClickEnabled = false;
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
            }
        }

        private void BackButton_Click(object sender, RoutedEventArgs e)
        {
            Frame.Navigate(typeof(Home));
        }
    }
}
