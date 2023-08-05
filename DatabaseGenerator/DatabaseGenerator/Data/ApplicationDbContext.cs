using DatabaseGenerator.Data.Entities;
using Marques.EFCore.SnakeCase;
using Microsoft.EntityFrameworkCore;
using Attribute = DatabaseGenerator.Data.Entities.Attribute;

namespace DatabaseGenerator.Data;

public class ApplicationDbContext : DbContext
{
	public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
	{

	}

	protected override void OnModelCreating(ModelBuilder modelBuilder)
	{
		base.OnModelCreating(modelBuilder);

		// modelBuilder.ToSnakeCase();

		modelBuilder.Entity<Account>(entity =>
		{
			entity.HasKey(x => x.Id);
			entity.Property(x => x.FirstName).HasMaxLength(50);
			entity.Property(x => x.LastName).HasMaxLength(50);
			entity.Property(x => x.Username).HasMaxLength(50);
			entity.Property(x => x.Email).HasMaxLength(100);
			entity.Property(x => x.Password).HasMaxLength(300);
			entity.Property(x => x.Role).HasMaxLength(10);
			entity.Property(x => x.IsAccountConfirmed);
			entity.Property(x => x.PhoneNumber).HasMaxLength(20);
			entity.Property(x => x.Location).HasMaxLength(50);

			entity
			.HasMany(x => x.Products)
			.WithOne(x => x.Account)
			.HasForeignKey(x => x.AccountId);

			entity
			.HasMany(x => x.Comments)
			.WithOne(x => x.Account)
			.HasForeignKey(x => x.AccountId);
		});

		modelBuilder.Entity<Attribute>(entity =>
		{
			// entity.ToTable("attribute");
			entity.HasKey(x => x.Id);
			entity.Property(x => x.Type).HasMaxLength(10);
			entity.Property(x => x.Name).HasMaxLength(50);

			entity
			.HasMany(x => x.Products)
			.WithMany(x => x.Attributes)
			.UsingEntity<ProductAttribute>(
				l => l.HasOne<Product>().WithMany().HasForeignKey(e => e.ProductId),
				r => r.HasOne<Attribute>().WithMany().HasForeignKey(e => e.AttributeId)
			);
		});

		modelBuilder.Entity<Category>(entity =>
		{
			entity.HasKey(x => x.Id);
			entity.Property(x => x.Name).HasMaxLength(50);

			entity
			.HasMany(x => x.Products)
			.WithOne(x => x.Category)
			.HasForeignKey(x => x.CategoryId);
		});

		modelBuilder.Entity<Comment>(entity =>
		{
			entity.HasKey(x => x.Id);
			entity.Property(x => x.Text).HasMaxLength(100);
			entity.Property(x => x.CreatedTime);

			entity
			.HasOne(x => x.Product)
			.WithMany(x => x.Comments)
			.HasForeignKey(x => x.ProductId);
		});

		modelBuilder.Entity<Product>(entity =>
		{
			entity.HasKey(x => x.Id);
			entity.Property(x => x.IsDeleted);
			entity.Property(x => x.Name).HasMaxLength(50);
			entity.Property(x => x.Description).HasMaxLength(50);
			entity.Property(x => x.CreatedTime);

			entity.HasMany(x => x.Photos).WithOne(x => x.Product).HasForeignKey(x => x.ProductId);
		});

		modelBuilder.Entity<Photo>(entity =>
		{
			entity.HasKey(x => x.Id);
			entity.Property(x => x.PhotoUrl).HasMaxLength(50);
			
		});

		modelBuilder.Entity<ProductAttribute>(entity =>
		{
			entity.HasKey(x => new { x.ProductId, x.AttributeId });
			entity.Property(x => x.Value).HasMaxLength(100);
		});

		modelBuilder.Entity<ReportMessage>(entity =>
		{
			entity.HasKey(x => x.Id);
			entity.Property(x => x.IsSeen);
			entity.Property(x => x.MessageText).HasMaxLength(100);
			entity.Property(x => x.CreatedTime);

			entity.HasOne(x => x.Product).WithMany(x => x.ReportMessages).HasForeignKey(x => x.ProductId);
			entity.HasOne(x => x.Account).WithMany(x => x.ReportMessages).HasForeignKey(x => x.AccountId);

		});
	}
}
