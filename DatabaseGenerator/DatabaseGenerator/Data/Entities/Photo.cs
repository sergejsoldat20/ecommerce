using Microsoft.AspNetCore.DataProtection.KeyManagement;
using Microsoft.AspNetCore.DataProtection;
using System.Xml.Linq;
using System.Security.Principal;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseGenerator.Data.Entities
{
	[Table("photo")]
	public class Photo
	{
		[Column("id")]
		public int Id { get; set; }
		[Column("photo_url")]
		public string PhotoUrl { get; set; }
		[Column("product_id")]
		public int ProductId { get; set; }
		public virtual Product Product { get; set; }
	}
}
