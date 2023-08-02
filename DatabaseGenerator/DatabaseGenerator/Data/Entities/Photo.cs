using Microsoft.AspNetCore.DataProtection.KeyManagement;
using Microsoft.AspNetCore.DataProtection;
using System.Xml.Linq;
using System.Security.Principal;

namespace DatabaseGenerator.Data.Entities
{
	public class Photo
	{
		public int Id { get; set; }
		public string PhotoUrl { get; set; }
		public int ProductId { get; set; }
		public virtual Product Product { get; set; }
	}
}
