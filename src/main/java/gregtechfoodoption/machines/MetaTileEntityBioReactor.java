/*
 * package gregtechfoodoption.machines;
 * 
 * 
 * public class MetaTileEntityBioReactor extends SimpleMachineMetaTileEntity implements ITieredMetaTileEntity {
 * public MetaTileEntityBioReactor(ResourceLocation metaTileEntityId, int tier) {
 * super(metaTileEntityId, GARecipeMaps.BIO_REACTOR_RECIPES, ClientHandler.ORGANIC_REPLICATOR_OVERLAY, tier);
 * }
 * 
 * @Override
 * public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
 * return new MetaTileEntityBioReactor(this.metaTileEntityId, this.getTier());
 * }
 * 
 * @Override
 * public IItemHandlerModifiable createImportItemHandler() {
 * return new ItemStackHandler(3);
 * }
 * 
 * @Override
 * public IItemHandlerModifiable createExportItemHandler() {
 * return new ItemStackHandler(3);
 * }
 * 
 * @Override
 * public FluidTankList createImportFluidHandler() {
 * return new FluidTankList(false, new FluidTank(32000), new FluidTank(32000), new FluidTank(32000));
 * }
 * 
 * @Override
 * protected FluidTankList createExportFluidHandler() {
 * return new FluidTankList(false, new FluidTank(32000), new FluidTank(32000));
 * }
 * }
 */
